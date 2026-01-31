package main;

import com.jogamp.opengl.GL2;

public class Alien {
    private float x, y, z;
    private float size = 0.3f;
    private boolean active = true;
    private float speed = 0.02f;
    private float rotation = 0.0f;
    private int direction = 1; // 1 pour droite, -1 pour gauche
    
    public Alien(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void update() {
        x += speed * direction;
        
        if (x > 3.0f || x < -3.0f) {
            direction *= -1;
            y -= 0.2f; // difficulté -> de combien vont descendre les aliens 
        }
        
        rotation += 2.0f;
        if (rotation > 360) rotation = 0;
    }
    
    public void display(GL2 gl) {
        if (!active) return;
        
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glRotatef(rotation, 0, 1, 0);
        
        // corps
        drawCube(gl, 0, 0, 0, size, 0.8f, 0.2f, 0.2f);
        
        // yeux
        drawCube(gl, -size/3, size/4, size/2, size/5, 0.0f, 1.0f, 0.0f);
        drawCube(gl, size/3, size/4, size/2, size/5, 0.0f, 1.0f, 0.0f);
        
        // antennes
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(0.9f, 0.1f, 0.1f);
        gl.glVertex3f(-size/3, size, 0);
        gl.glVertex3f(-size/2, size + size/2, 0);
        gl.glVertex3f(size/3, size, 0);
        gl.glVertex3f(size/2, size + size/2, 0);
        gl.glEnd();
        
        // boules
        drawSphere(gl, -size/2, size + size/2, 0, size/8, 1.0f, 0.5f, 0.0f);
        drawSphere(gl, size/2, size + size/2, 0, size/8, 1.0f, 0.5f, 0.0f);
        
        gl.glPopMatrix();
    }
    
    private void drawCube(GL2 gl, float cx, float cy, float cz, float s, float r, float g, float b) {
        gl.glPushMatrix();
        gl.glTranslatef(cx, cy, cz);
        
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(r, g, b);
        
        // avant
        gl.glVertex3f(-s/2, -s/2, s/2);
        gl.glVertex3f(s/2, -s/2, s/2);
        gl.glVertex3f(s/2, s/2, s/2);
        gl.glVertex3f(-s/2, s/2, s/2);
        
        // arrière
        gl.glVertex3f(-s/2, -s/2, -s/2);
        gl.glVertex3f(-s/2, s/2, -s/2);
        gl.glVertex3f(s/2, s/2, -s/2);
        gl.glVertex3f(s/2, -s/2, -s/2);
        
        // gauche
        gl.glVertex3f(-s/2, -s/2, -s/2);
        gl.glVertex3f(-s/2, -s/2, s/2);
        gl.glVertex3f(-s/2, s/2, s/2);
        gl.glVertex3f(-s/2, s/2, -s/2);
        
        // droite
        gl.glVertex3f(s/2, -s/2, -s/2);
        gl.glVertex3f(s/2, s/2, -s/2);
        gl.glVertex3f(s/2, s/2, s/2);
        gl.glVertex3f(s/2, -s/2, s/2);
        
        // dessus
        gl.glVertex3f(-s/2, s/2, -s/2);
        gl.glVertex3f(-s/2, s/2, s/2);
        gl.glVertex3f(s/2, s/2, s/2);
        gl.glVertex3f(s/2, s/2, -s/2);
        
        // dessous
        gl.glVertex3f(-s/2, -s/2, -s/2);
        gl.glVertex3f(s/2, -s/2, -s/2);
        gl.glVertex3f(s/2, -s/2, s/2);
        gl.glVertex3f(-s/2, -s/2, s/2);
        
        gl.glEnd();
        gl.glPopMatrix();
    }
    
    private void drawSphere(GL2 gl, float cx, float cy, float cz, float radius, float r, float g, float b) {
        gl.glPushMatrix();
        gl.glTranslatef(cx, cy, cz);
        gl.glColor3f(r, g, b);
        
        int slices = 8;
        int stacks = 8;
        
        for (int i = 0; i < stacks; i++) {
            float lat0 = (float)Math.PI * (-0.5f + (float)(i) / stacks);
            float z0 = (float)Math.sin(lat0);
            float zr0 = (float)Math.cos(lat0);
            
            float lat1 = (float)Math.PI * (-0.5f + (float)(i + 1) / stacks);
            float z1 = (float)Math.sin(lat1);
            float zr1 = (float)Math.cos(lat1);
            
            gl.glBegin(GL2.GL_QUAD_STRIP);
            for (int j = 0; j <= slices; j++) {
                float lng = 2 * (float)Math.PI * (float)(j) / slices;
                float x = (float)Math.cos(lng);
                float y = (float)Math.sin(lng);
                
                gl.glVertex3f(x * zr0 * radius, y * zr0 * radius, z0 * radius);
                gl.glVertex3f(x * zr1 * radius, y * zr1 * radius, z1 * radius);
            }
            gl.glEnd();
        }
        
        gl.glPopMatrix();
    }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float getSize() { return size; }
}
