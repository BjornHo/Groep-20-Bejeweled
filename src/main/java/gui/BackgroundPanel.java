package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *  Support custom painting on a panel in the form of
 *  a) images - that can be scaled, tiled or painted at original size
 *  b) non solid painting - that can be done by using a Paint object
 *  Also, any component added directly to this panel will be made
 *  non-opaque so that the custom painting can show through.
 *  
 *  @Author - Rob Camick (October 12, 2008)
 *  @Source - https://tips4java.wordpress.com/2008/10/12/background-panel/
 */
public class BackgroundPanel extends JPanel {

	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int SCALED = 0;
	public static final int TILED = 1;
	public static final int ACTUAL = 2;

	private Paint painter;
	private Image image;
	private int style = SCALED;
	private float alignmentX = 0.5f;
	private float alignmentY = 0.5f;
	private boolean isTransparentAdd = true;

	/**
	 * Set image as the background with the SCALED style
	 * 
	 * @param image
	 *     Image to set.
	 */
	public BackgroundPanel(Image image) {
		this(image, SCALED);
	}
	
	/**
	 * Set image as the background with the specified style
	 * 
	 * @param image
	 *     Image to be set as background.
	 * @param style
	 *     Style to be specified.
	 */
	public BackgroundPanel(Image image, int style) {
		setImage(image);
		setStyle(style);
		setLayout(new BorderLayout());
	}

	/**
	 * Set image as the background with the specified style and alignment
	 * 
	 * @param image
	 *     Image to be set as background
	 * @param style
	 *     Style to be specified.
	 * @param alignmentX
	 *     x-value to align to.
	 * @param alignmentY
	 *     y-value to align to.
	 */
	public BackgroundPanel(Image image, int style, float alignmentX, float alignmentY) {
		setImage(image);
		setStyle(style);
		setImageAlignmentX(alignmentX);
		setImageAlignmentY(alignmentY);
		setLayout(new BorderLayout());
	}

	/**
	 * Use the Paint interface to paint a background
	 *  
	 * @param painter
	 *     Paint to be used.
	 */
	public BackgroundPanel(Paint painter) {
		setPaint(painter);
		setLayout(new BorderLayout());
	}

	/**
	 * Set the image used as the background
	 *
	 * @param image
	 *     Image to be set.
	 */
	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	/**
	 * Set the style used to paint the background image
	 *
	 * @param int
	 *     Style to be set.
	 */
	public void setStyle(int style) {
		this.style = style;
		repaint();
	}

	/**
	 * Set the Paint object used to paint the background
	 * 
	 * @param painter
	 *     Paint to be set.
	 */
	public void setPaint(Paint painter) {
		this.painter = painter;
		repaint();
	}

	/**
	 * Specify the horizontal alignment of the image when using ACTUAL style
	 * 
	 * @param alignmentX
	 *     x-value of alignment.
	 */
	public void setImageAlignmentX(float alignmentX) {
		this.alignmentX = alignmentX > 1.0f ? 1.0f : alignmentX < 0.0f ? 0.0f : alignmentX;
		repaint();
	}

	/**
	 * Specify the horizontal alignment of the image when using ACTUAL style
	 * 
	 * @param aligntmentY
	 *     y-value of alignment.
	 */
	public void setImageAlignmentY(float alignmentY) {
		this.alignmentY = alignmentY > 1.0f ? 1.0f : alignmentY < 0.0f ? 0.0f : alignmentY;
		repaint();
	}

	/**
	 * Override method so we can make the component transparent.
	 * 
	 * @param component
	 *     Component to be added.
	 */
	public void add(JComponent component) {
		add(component, null);
	}
	
	/**
	 * Override method so we can make the component transparent.
	 * 
	 * @param component
	 *     Component to be added.
	 * @param constraints
	 *     (Object) constraints to be specified.
	 */
	public void add(JComponent component, Object constraints) {
		if (isTransparentAdd) {
			makeComponentTransparent(component);
		}
		super.add(component, constraints);
	}

	/**
	 * Override to provide a preferred size equal to the image size.
	 */
	@Override
	public Dimension getPreferredSize() {
		if (image == null) {
			return super.getPreferredSize();
		} else {
			return new Dimension(image.getWidth(null), image.getHeight(null));
		}
	}

	/**
	 * Controls whether components added to this panel should automatically
	 * be made transparent. That is, setOpaque(false) will be invoked.
	 * The default is set to true.
	 * 
	 * @param isTransparantAdd
	 *     Boolean that determines transparancy.
	 */
	public void setTransparentAdd(boolean isTransparentAdd) {
		this.isTransparentAdd = isTransparentAdd;
	}

	/**
	 * Try to make the component transparent.
	 * For components that use renderers, like JTable, you will also need to
	 * change the renderer to be transparent. An easy way to do this is to
	 * set the background of the table to a Color using an alpha value of 0.
	 * 
	 * @param component
	 *     JComponent to be made transparant.
	 */
	private void makeComponentTransparent(JComponent component) {
		component.setOpaque( false );

		if (component instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane)component;
			JViewport viewport = scrollPane.getViewport();
			viewport.setOpaque( false );
			Component comp = viewport.getView();

			if (comp instanceof JComponent) {
				((JComponent)comp).setOpaque( false );
			}
		}
	}

	/**
	 * Add custom painting.
	 * 
	 * @param gra
	 *     Graphics Object for which the custom paint will be added.
	 */
	@Override
	protected void paintComponent(Graphics gra) {
		super.paintComponent(gra);

		//  Invoke the painter for the background
		if (painter != null) {
			Dimension dim = getSize();
			Graphics2D g2 = (Graphics2D) gra;
			g2.setPaint(painter);
			g2.fill( new Rectangle(0, 0, dim.width, dim.height) );
		}

		//  Draw the image
		if (image == null ) {
			return;
		}
		switch (style) {
			case SCALED :
				drawScaled(gra);
				break;
			case TILED  :
				drawTiled(gra);
				break;
			case ACTUAL :
				drawActual(gra);
				break;
			default:
            	drawScaled(gra);
		}
	}

	/**
	 * Custom painting code for drawing a SCALED image as the background
	 * 
	 * @param gra
	 *     Graphics Object for which the Image will be drawn.
	 */
	private void drawScaled(Graphics gra) {
		Dimension dim = getSize();
		gra.drawImage(image, 0, 0, dim.width, dim.height, null);
	}

	/**
	 * Custom painting code for drawing TILED images as the background
	 *  
	 * @param gra
	 *     Graphics object for which image will be drawn.
	 */
	private void drawTiled(Graphics gra) {
		   Dimension dim = getSize();
		   int width = image.getWidth(null);
		   int height = image.getHeight(null);

		   for (int x = 0; x < dim.width; x += width) {
			   for (int y = 0; y < dim.height; y += height) {
				   gra.drawImage(image, x, y, null, null);
			   }
		   }
	   }
	

	/**
	 * Custom painting code for drawing the ACTUAL image as the background.
	 * The image is positioned in the panel based on the horizontal and
	 * vertical alignments specified.
	 * 
	 * @param gra
	 *     Graphics Object for which the image will be drawn.
	 */
	private void drawActual(Graphics gra) {
		Dimension dim = getSize();
		Insets insets = getInsets();
		int width = dim.width - insets.left - insets.right;
		int height = dim.height - insets.top - insets.left;
		float xcoord = (width - image.getWidth(null)) * alignmentX;
		float ycoord = (height - image.getHeight(null)) * alignmentY;
		gra.drawImage(image, (int)xcoord + insets.left, (int)ycoord + insets.top, this);
	}
}