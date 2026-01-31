package main;

import com.jogamp.opengl.GL2;

public class Bullet {
    private float x, y, z;
    private float speed = 0.2f;
    private boolean active = true;
    private float size = 0.1f;
    
    public Bullet(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void update() {
        y += speed;
        if (y > 5.0f) {
            active = false;
        }
    }
    
    public void display(GL2 gl) {
        if (!active) return;
        
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(-size/2, size, 0);
        gl.glVertex3f(size/2, size, 0);
        gl.glVertex3f(size/2, -size, 0);
        gl.glVertex3f(-size/2, -size, 0);
        gl.glEnd();
        
        gl.glPopMatrix();
    }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
}
