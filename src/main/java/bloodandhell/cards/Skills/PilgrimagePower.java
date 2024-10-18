package bloodandhell.cards.Skills;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class PilgrimagePower extends AbstractPower {
    public static final String POWER_ID = "PilgrimagePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PilgrimagePower(AbstractCreature owner, int turns) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        updateDescription();
        //loadRegion("pilgrimage");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        if (this.amount == 1) {
            addToBot(new RemoveDebuffsAction(this.owner));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}
