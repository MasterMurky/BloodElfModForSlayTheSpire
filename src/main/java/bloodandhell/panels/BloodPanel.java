package bloodandhell.panels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

public class BloodPanel extends AbstractPanel {
    private static final Color MPTextColor = new Color(0.9F, 1.0F, 1.0F, 1.0F);

    private float current_x;
    private float current_y;

    public BloodPanel() {
        super(94.0F * Settings.xScale, 414.0F * Settings.yScale, -720.0F * Settings.xScale, 540.0F * Settings.yScale, 12.0F * Settings.scale, -12.0F * Settings.scale, null, true);
    }

    public void render(SpriteBatch sb) {
        if (this.img != null) {
            sb.setColor(Color.WHITE);
            sb.draw(this.img, this.current_x, this.current_y, this.img_width, this.img_height, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        }

    }
}
