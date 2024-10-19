package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class PilgrimagePower extends AbstractPower {
    public static final String POWER_ID = "bloodandhell:pilgrimagePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PilgrimagePower(AbstractCreature owner, int turns) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        this.type = AbstractPower.PowerType.BUFF; // Définir le type du pouvoir (si applicable)
        this.isTurnBased = true; // Indiquer si le pouvoir est basé sur le tour
        this.loadRegion("defaultPower"); // Charger une région d'image par défaut
        updateDescription();
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) { // Vérifier que c'est bien le tour du joueur
            if (this.amount == 1) {
                // Retirer les debuffs lorsque le pouvoir s'épuise
                addToBot(new RemoveDebuffsAction(this.owner));
            }
            // Réduire la durée du pouvoir
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1]; // Description pour le dernier tour
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]; // Description pour plusieurs tours
        }
    }
}
