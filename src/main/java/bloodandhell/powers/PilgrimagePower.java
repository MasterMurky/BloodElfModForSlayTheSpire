package bloodandhell.powers;

import bloodandhell.util.GeneralUtils;
import bloodandhell.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class PilgrimagePower extends AbstractPower {
    public static final String POWER_ID = bloodandhell.BasicMod.makeID("pilgrimagePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // atEndOfTurn se déclenche aussi à la fin du tour où la carte est jouée (confirmé
    // empiriquement) : on ignore ce tout premier déclenchement pour que "amount" corresponde
    // exactement au nombre de tours FUTURS où les malus seront retirés.
    private boolean skippedPlayTurn = false;

    public PilgrimagePower(AbstractCreature owner, int turns) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        this.type = AbstractPower.PowerType.BUFF; // Définir le type du pouvoir (si applicable)
        this.isTurnBased = true; // Indiquer si le pouvoir est basé sur le tour
        updateDescription();

        // "path_to_victory" (ancien loadRegion) ne résout à rien : region128/img restaient tous
        // les deux null, et le premier flash() (déclenché par ApplyPowerAction dès l'application
        // du pouvoir) plantait avec un NullPointerException dans FlashPowerEffect.
        String unPrefixed = GeneralUtils.removePrefix(POWER_ID);
        Texture normalTexture = TextureLoader.getPowerTextureNull(unPrefixed);
        Texture hiDefImage = TextureLoader.getHiDefPowerTexture(unPrefixed);
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null) {
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
            }
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) {
            return;
        }
        if (!this.skippedPlayTurn) {
            this.skippedPlayTurn = true;
            return;
        }

        // Retire les malus à la fin de CHACUN des tours restants (pas seulement le dernier).
        addToBot(new RemoveDebuffsAction(this.owner));
        if (this.amount <= 1) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        // Toujours le même format : "...Dure X tours.", X se met à jour dynamiquement avec
        // le nombre de tours restants (plus de cas particulier pour le dernier tour).
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
