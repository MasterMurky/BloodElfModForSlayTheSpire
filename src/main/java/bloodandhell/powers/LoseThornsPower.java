package bloodandhell.powers;

import bloodandhell.util.TextureLoader;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LoseThornsPower extends AbstractPower {
    public static final String POWER_ID = "bloodandhell:LoseThornsPower";
    public static final PowerType POWER_TYPE = PowerType.DEBUFF;

    public LoseThornsPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = POWER_TYPE;
        this.amount = amount;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.description = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS[0] + this.amount + CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS[1];

        this.setImage("bloodandhell/images/powers/ThornsDown84.png", "bloodandhell/images/powers/ThornsDown32.png"); // problème visuel à fix
    }

    private void setImage(String largeImagePath, String smallImagePath) {
        this.img = TextureLoader.getTexture(largeImagePath);
        this.region128 = new com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion(this.img, 0, 0, 84, 84);
        this.region48 = new com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion(TextureLoader.getTexture(smallImagePath), 0, 0, 32, 32);
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
