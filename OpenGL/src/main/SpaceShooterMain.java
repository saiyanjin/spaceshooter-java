package main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class SpaceShooterMain extends GLCanvas implements GLEventListener {
    
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Alien> aliens;
    private ParticleSystem particleSystem;
    
    private long lastShootTime = 0;
    private long shootCooldown = 200; 
    
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private int scoreToWin = 500; 
    
    public SpaceShooterMain() {
        this.addGLEventListener(this);
        this.bullets = new ArrayList<Bullet>();
        this.aliens = new ArrayList<Alien>();
        this.particleSystem = new ParticleSystem();
    }
    
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.1f, 1.0f); // fond
        
        player = new Player(0, -2.0f, 0);
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                float x = -2.0f + col * 1.0f;
                float y = 1.5f - row * 0.8f;
                aliens.add(new Alien(x, y, 0));
            }
        }
        
        // clavier
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_Q:
                        player.setLeft(true);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        player.setRight(true);
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_Z:
                        player.setUp(true);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        player.setDown(true);
                        break;
                    case KeyEvent.VK_SPACE:
                        shoot();
                        break;
                    case KeyEvent.VK_R:
                        if (gameOver || gameWon) {
                            resetGame();
                        }
                        break;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_Q:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_Z:
                        player.setUp(false);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        player.setDown(false);
                        break;
                }
            }
        });
        
        this.setFocusable(true);
        this.requestFocus();
    }
    
    private void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime > shootCooldown) {
            bullets.add(new Bullet(player.getX(), player.getY() + 0.3f, player.getZ()));
            lastShootTime = currentTime;
        }
    }
    
    private void resetGame() {
        score = 0;
        gameOver = false;
        gameWon = false;
        bullets.clear();
        aliens.clear();
        
        player = new Player(0, -2.0f, 0);
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                float x = -2.0f + col * 1.0f;
                float y = 1.5f - row * 0.8f;
                aliens.add(new Alien(x, y, 0));
            }
        }
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        final GLU glu = new GLU();
        float aspect = (float)width / height;
        
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glHint(GL2.GL_POINT_SMOOTH_HINT, GL2.GL_NICEST);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        // cam
        gl.glTranslatef(0, 0, -8);
        
        if (!gameOver && !gameWon) {
            // maj joueur
            player.update();
            player.display(gl);
            
            // maj balles
            Iterator<Bullet> bulletIter = bullets.iterator();
            while (bulletIter.hasNext()) {
                Bullet bullet = bulletIter.next();
                bullet.update();
                bullet.display(gl);
                
                if (!bullet.isActive()) {
                    bulletIter.remove();
                }
            }
            
            // maj aliens
            Iterator<Alien> alienIter = aliens.iterator();
            while (alienIter.hasNext()) {
                Alien alien = alienIter.next();
                alien.update();
                alien.display(gl);
                
                // aliens en bas
                if (alien.getY() < -3.0f) {
                    gameOver = true;
                }
            }
            
            checkCollisions();
            
            if (score >= scoreToWin) {
                gameWon = true;
            }
            
            if (aliens.isEmpty() && !gameWon) {
                
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 5; col++) {
                        float x = -2.0f + col * 1.0f;
                        float y = 1.5f - row * 0.8f;
                        aliens.add(new Alien(x, y, 0));
                    }
                }
            }
        }
        
        particleSystem.update();
        particleSystem.display(gl);
        
        drawHUD(gl);
    }
    
    private void checkCollisions() {
        // collisions balle -> aliens
        Iterator<Bullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet bullet = bulletIter.next();
            
            Iterator<Alien> alienIter = aliens.iterator();
            while (alienIter.hasNext()) {
                Alien alien = alienIter.next();
                
                float dx = bullet.getX() - alien.getX();
                float dy = bullet.getY() - alien.getY();
                float dz = bullet.getZ() - alien.getZ();
                float distance = (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
                
                if (distance < (alien.getSize() + 0.1f)) {
                    
                    alien.setActive(false);
                    bullet.setActive(false);
                    score += 10;
                    
                    particleSystem.createExplosion(alien.getX(), alien.getY(), alien.getZ(), 
                                                   1.0f, 0.5f, 0.0f);
                    
                    alienIter.remove();
                    break;
                }
            }
            
            if (!bullet.isActive()) {
                bulletIter.remove();
            }
        }
        
        for (Alien alien : aliens) {
            float dx = player.getX() - alien.getX();
            float dy = player.getY() - alien.getY();
            float dz = player.getZ() - alien.getZ();
            float distance = (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
            
            if (distance < (alien.getSize() + player.getSize())) {
                gameOver = true;
                particleSystem.createExplosion(player.getX(), player.getY(), player.getZ(), 
                                               0.0f, 0.7f, 1.0f);
                break;
            }
        }
    }
    
    private void drawHUD(GL2 gl) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(-4, 4, -3, 3, -1, 1);
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        
        gl.glDisable(GL2.GL_DEPTH_TEST);
        
        if (gameOver) {
            gl.glEnable(GL2.GL_BLEND);
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glColor4f(0.0f, 0.0f, 0.0f, 0.7f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-3.5f, 1.2f);
            gl.glVertex2f(3.5f, 1.2f);
            gl.glVertex2f(3.5f, -1.2f);
            gl.glVertex2f(-3.5f, -1.2f);
            gl.glEnd();
            
            gl.glLineWidth(3.0f);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            TextRenderer.drawString(gl, "GAME OVER", -2.8f, 0.3f, 0.5f);
            
            gl.glLineWidth(2.0f);
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            TextRenderer.drawString(gl, "R POUR RELANCER", -2.6f, -0.5f, 0.3f);
            
            
            gl.glLineWidth(1.0f);
        }
        
        if (gameWon) {
            gl.glEnable(GL2.GL_BLEND);
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glColor4f(0.0f, 0.0f, 0.0f, 0.7f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-3.5f, 1.2f);
            gl.glVertex2f(3.5f, 1.2f);
            gl.glVertex2f(3.5f, -1.2f);
            gl.glVertex2f(-3.5f, -1.2f);
            gl.glEnd();
            
            gl.glLineWidth(3.0f);
            gl.glColor3f(0.0f, 1.0f, 0.0f);
            TextRenderer.drawString(gl, "YOU WIN", -2.2f, 0.3f, 0.6f);
            
            gl.glLineWidth(2.0f);
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            TextRenderer.drawString(gl, "R POUR RELANCER", -2.6f, -0.5f, 0.3f);
            
            
            gl.glLineWidth(1.0f);
        }
        
        gl.glColor3f(0.3f, 0.3f, 0.3f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-3.8f, 2.8f);
        gl.glVertex2f(-0.8f, 2.8f);
        gl.glVertex2f(-0.8f, 2.5f);
        gl.glVertex2f(-3.8f, 2.5f);
        gl.glEnd();
        
        float scoreBarWidth = (score / (float)scoreToWin) * 3.0f;
        if (scoreBarWidth > 3.0f) scoreBarWidth = 3.0f;
        
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-3.8f, 2.8f);
        gl.glVertex2f(-3.8f + scoreBarWidth, 2.8f);
        gl.glVertex2f(-3.8f + scoreBarWidth, 2.5f);
        gl.glVertex2f(-3.8f, 2.5f);
        gl.glEnd();
        
        gl.glLineWidth(1.5f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        String scoreText = String.valueOf(score);
        TextRenderer.drawString(gl, scoreText, -3.7f, 2.55f, 0.2f);
        gl.glLineWidth(1.0f);
        
        gl.glEnable(GL2.GL_DEPTH_TEST);
        
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
    
    public void dispose(GLAutoDrawable drawable) {}
    
    public static void main(String[] args) {
        GLCanvas canvas = new SpaceShooterMain();
        canvas.setPreferredSize(new Dimension(800, 600));
        
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setTitle("Space Shooter - OpenGL");
        frame.pack();
        frame.setVisible(true);
        
        final Animator animator = new Animator(canvas);
        animator.start();
    }
}
