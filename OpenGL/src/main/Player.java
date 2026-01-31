package main;

import com.jogamp.opengl.GL2;

public class Player {
    private float x, y, z;
    private float speed = 0.1f;
    private float size = 0.3f;
    private boolean left, right, up, down;
    
    public Player(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void update() {
        if (left) x -= speed;
        if (right) x += speed;
        //if (up) y += speed; -> si je veux aller vers le haut
        //if (down) y -= speed; -> si je veux aller vers le bas 
        
        if (x < -3.0f) x = -3.0f;
        if (x > 3.0f) x = 3.0f;
        if (y < -2.5f) y = -2.5f;
        if (y > 0.0f) y = 0.0f;
    }
    
    public void display(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        
        // le vaisseau
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(0.0f, 0.7f, 1.0f);
        
        // avant
        gl.glVertex3f(0.0f, size, 0.0f);
        gl.glVertex3f(-size/2, -size/2, size/2);
        gl.glVertex3f(size/2, -size/2, size/2);
        
        // droite
        gl.glColor3f(0.0f, 0.5f, 0.8f);
        gl.glVertex3f(0.0f, size, 0.0f);
        gl.glVertex3f(size/2, -size/2, size/2);
        gl.glVertex3f(size/2, -size/2, -size/2);
        
        // arrière
        gl.glColor3f(0.0f, 0.6f, 0.9f);
        gl.glVertex3f(0.0f, size, 0.0f);
        gl.glVertex3f(size/2, -size/2, -size/2);
        gl.glVertex3f(-size/2, -size/2, -size/2);
        
        // gauche
        gl.glColor3f(0.0f, 0.5f, 0.8f);
        gl.glVertex3f(0.0f, size, 0.0f);
        gl.glVertex3f(-size/2, -size/2, -size/2);
        gl.glVertex3f(-size/2, -size/2, size/2);
        
        gl.glEnd();
        
        // base
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 0.4f, 0.6f);
        gl.glVertex3f(-size/2, -size/2, size/2);
        gl.glVertex3f(size/2, -size/2, size/2);
        gl.glVertex3f(size/2, -size/2, -size/2);
        gl.glVertex3f(-size/2, -size/2, -size/2);
        gl.glEnd();
        
        gl.glPopMatrix();
    }
    
    public void setLeft(boolean left) { 
    	this.left = left; 
    }
    
    public void setRight(boolean right) { 
    	this.right = right; 
    }
    
    public void setUp(boolean up) { 
    	this.up = up; 
    }
    
    public void setDown(boolean down) {
    	this.down = down; 
    }
    
    public float getX() { 
    	return x; 
    }
    
    public float getY() { 
    	return y; 
    }
    
    public float getZ() { 
    	return z; 
    }
    
    public float getSize() { 
    	return size; 
    }
}
