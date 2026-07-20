package bloodandhell.powers;

import bloodandhell.util.GeneralUtils;
import bloodandhell.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LoseThornsPower extends AbstractPower {
    public static final String POWER_ID = bloodandhell.BasicMod.makeID("LoseThornsPower");
    public static final PowerType POWER_TYPE = PowerType.DEBUFF;

    public LoseThornsPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = POWER_TYPE;
        this.amount = amount;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();

        // Même schéma de chargement que DexterityLossPower : quand la version large/ existe,
        // this.img reste null -- l'assigner (ancien code) faisait dessiner la texture 84x84 à
        // taille native sous le joueur, d'où une icône géante.
        String unPrefixed = GeneralUtils.removePrefix(POWER_ID);
        Texture normalTexture = TextureLoader.getPowerTextureNull(unPrefixed);
        Texture hiDefImage = TextureLoader.getHiDefPowerTexture(unPrefixed);
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, LoseThornsPower.POWER_ID));

        if (this.owner.hasPower(ThornsPower.POWER_ID)) {
            ThornsPower thornsPower = (ThornsPower) this.owner.getPower(ThornsPower.POWER_ID);

            if (!this.owner.hasPower(ArtifactPower.POWER_ID)) {
                if (thornsPower.amount <= this.amount) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ThornsPower.POWER_ID));
                } else {
                    thornsPower.reducePower(this.amount);
                }
            } else {
                this.owner.getPower(ArtifactPower.POWER_ID).onSpecificTrigger();
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS[0] + this.amount + CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS[1];
    }
}
