import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.GLUT;


public class Graphics implements GLEventListener
{
    private GLU glu;
    private GLUT glut;
    private int w, h;
      
    public Graphics()
    {  
    }
    
    public void init(GLAutoDrawable drawable) 
    {
    	w = drawable.getWidth();
        h = drawable.getHeight();
        
        GL gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        
        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glClearDepth(1.0f);
        gl.glClearDepth(1.0f);												// Depth Buffer Setup
    	gl.glEnable(GL.GL_DEPTH_TEST);										// Enables Depth Testing
    	gl.glDepthFunc(GL.GL_LEQUAL);										// The Type Of Depth Test To Do
    	gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);			// Really Nice Perspective Calculations
    }

    public void display(GLAutoDrawable drawable) 
    {
        GL gl = drawable.getGL();
        
        w = drawable.getWidth();
        h = drawable.getHeight();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);			// Clear the colour and depth buffer
          
        gl.glViewport(0, 0, w, h);											// Reset The Current Viewport
        
        gl.glMatrixMode(GL.GL_PROJECTION);									// Select The Projection Matrix
        gl.glLoadIdentity();												// Reset The Projection Matrix
    	
        glu.gluPerspective(45.0f,(float)w/(float)h,0.1f,100.0f);			// Calculate The Aspect Ratio Of The Window

        gl.glMatrixMode(GL.GL_MODELVIEW);									// Select The Modelview Matrix
        gl.glLoadIdentity();												// Reset The Modelview Matrix     

        drawScene(drawable);												// Draw the scene
    }

	public void reshape(GLAutoDrawable drawable, int x, int y, int w2, int h2) 
    {
		GL gl = drawable.getGL();
        
        w2 = drawable.getWidth();
        h2 = drawable.getHeight();
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        // perspective view
        gl.glViewport(10, 10, w-20, h-20);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f,(float)w/(float)h,0.1f,100.0f);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) 
    {      
    }
    
    public void drawScene(GLAutoDrawable drawable)
    {
    	// Drawing code goes here
    	GL gl = drawable.getGL();
    	
//    	gl.glPushMatrix();
//        gl.glTranslatef(-2.0f,-1.5f,-8.0f);       // Move left 2.0 units, up 1.5 units, and back 8 units
//        gl.glBegin(GL.GL_TRIANGLE_STRIP);         // Begin drawing triangle strip
//        gl.glVertex3f(-1.0f,-0.5f, 0.0f);         // V1 Triangle 1
//        gl.glVertex3f( 1.0f,-0.5f, 0.0f);         // V2 Triangle 1
//        gl.glVertex3f( 0.0f, 0.5f, 0.0f);         // V3 Triangle 2
//        gl.glVertex3f( 1.5f, 0.0f, 0.0f);         // V4 Triangle 2
//        gl.glVertex3f( 2.0f, -1.5f, 0.0f);        // V5 Triangle 2
//        gl.glEnd();                               // Finish drawing triangle strip
//        gl.glPopMatrix();	
    	gl.glPushMatrix();
        gl.glTranslatef(2.0f,-1.5f,-8.0f);        // Move right 2.0 units, up 1.5 units, and back 8 units
 
        gl.glBegin(GL.GL_TRIANGLE_FAN);           // Begin drawing triangle fan
        gl.glVertex3f( 0.0f, 0.0f, -4.0f);        // V1
        gl.glVertex3f( 0.0f, 1.5f, -4.0f);        // V2
        gl.glVertex3f( 1.5f,  0.0f, -4.0f);       // V3
 
        gl.glVertex3f( 1.5f, -1.5f, -4.0f);       // V6
        gl.glVertex3f( 0.0f,  -1.5f, -4.0f);      // V4
        gl.glVertex3f(-1.5f, 0f, -4.0f);          // V5
        gl.glEnd();                               // Finish drawing triangle fan
gl.glPopMatrix();
    }
}


