package ini.trakem2.display.graphics;

import ini.trakem2.display.Displayable;
import ini.trakem2.display.Paintable;
import ini.trakem2.display.Layer;
import ini.trakem2.display.Patch;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TestGraphicsSource implements GraphicsSource {

	/** Replaces all Patch instances by a smiley face. */
	public Collection<? extends Paintable> asPaintable(final Collection<? extends Paintable> ds) {
		final ArrayList<Paintable> a = new ArrayList<Paintable>();
		for (final Paintable p : ds) {
			if (p instanceof Patch) {
				final Paintable pa = new Paintable() {
					public void paint(Graphics2D g, double magnification, boolean active, int channels, Layer active_layer) {
						Patch patch = (Patch)p;
						Rectangle r = patch.getBoundingBox();
						g.setColor(Color.magenta);
						g.fillRect(r.x, r.y, r.width, r.height);
						g.setColor(Color.green);
						g.fillOval(r.width/3, r.height/3, r.width/10, r.width/10);
						g.fillOval(2 * (r.width/3), r.height/3, r.width/10, r.width/10);
						g.drawOval(r.width/2, 2*(r.height/3), r.width/3, r.height/6);
					}
					public void prePaint(Graphics2D g, double magnification, boolean active, int channels, Layer active_layer) {
						this.paint(g, magnification, active, channels, active_layer);
					}
				};
				a.add(pa);
			} else {
				a.add(p);
			}
		}
		return a;
	}
}