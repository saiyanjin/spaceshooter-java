package main;

import com.jogamp.opengl.GL2;
import java.util.ArrayList;
import java.util.Iterator;

public class ParticleSystem {
    private ArrayList<Particle> particles;
    
    public ParticleSystem() {
        particles = new ArrayList<Particle>();
    }
    
    public void createExplosion(float x, float y, float z, float r, float g, float b) {
        int numParticles = 20;
        
        for (int i = 0; i < numParticles; i++) {
            float vx = (float)(Math.random() - 0.5) * 0.2f;
            float vy = (float)(Math.random() - 0.5) * 0.2f;
            float vz = (float)(Math.random() - 0.5) * 0.2f;
            
            float pr = r + (float)(Math.random() - 0.5) * 0.2f;
            float pg = g + (float)(Math.random() - 0.5) * 0.2f;
            float pb = b + (float)(Math.random() - 0.5) * 0.2f;
            
            particles.add(new Particle(x, y, z, vx, vy, vz, pr, pg, pb));
        }
    }
    
    public void update() {
        Iterator<Particle> iter = particles.iterator();
        while (iter.hasNext()) {
            Particle p = iter.next();
            p.update();
            if (!p.isActive()) {
                iter.remove();
            }
        }
    }
    
    public void display(GL2 gl) {
        for (Particle p : particles) {
            p.display(gl);
        }
    }
    
    public int getParticleCount() {
        return particles.size();
    }
}
