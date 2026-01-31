package main;

import com.jogamp.opengl.GL2;

public class Particle {
    private float x, y, z;
    private float vx, vy, vz; // Vélocité
    private float r, g, b;
    private float life;
    private float size;
    private boolean active = true;
    
    public Particle(float x, float y, float z, float vx, float vy, float vz, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.r = r;
        this.g = g;
        this.b = b;
        this.life = 1.0f;
        this.size = 0.08f;
    }
    
    public void update() {
        if (!active) return;
        
        // Mise à jour de la position
        x += vx;
        y += vy;
        z += vz;
        
        // Gravité
        vy -= 0.005f;
        
        // Diminution de la vie
        life -= 0.02f;
        if (life <= 0) {
            active = false;
        }
    }
    
    public void display(GL2 gl) {
        if (!active) return;
        
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        
        // Particule carrée
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor4f(r, g, b, life); // Utiliser life pour la transparence
        
        // Face avant
        gl.glVertex3f(-size, -size, size);
        gl.glVertex3f(size, -size, size);
        gl.glVertex3f(size, size, size);
        gl.glVertex3f(-size, size, size);
        
        // Face arrière
        gl.glVertex3f(-size, -size, -size);
        gl.glVertex3f(-size, size, -size);
        gl.glVertex3f(size, size, -size);
        gl.glVertex3f(size, -size, -size);
        
        gl.glEnd();
        
        gl.glPopMatrix();
    }
    
    public boolean isActive() { return active; }
}
