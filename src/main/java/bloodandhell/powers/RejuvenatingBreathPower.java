package bloodandhell.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RejuvenatingBreathPower extends AbstractPower {
    public static final String POWER_ID = "RejuvenatingBreathCostPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private AbstractCard card;

    public RejuvenatingBreathPower(AbstractPlayer player, AbstractCard card) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = player;
        this.card = card;
        this.amount = owner.currentHealth % 10;  // Le coût est basé sur les *unités* des PV du joueur
        updateDescription();
        loadRegion("power");
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onInitialApplication() {
        // On met à jour le coût de la carte au début
        updateCardCost();
    }

    // Méthode qui met à jour le coût de la carte
    private void updateCardCost() {
        this.card.cost = this.amount;  // Le coût de la carte est basé sur les unités de PV
        this.card.isCostModifiedForTurn = true;  // Indique que le coût a été modifié pour ce tour
    }

    // Cette méthode sera appelée chaque fois que les PV du joueur changent
    public void onPlayerHealthChanged() {
        this.amount = owner.currentHealth % 10;  // Recalcule le coût en fonction des unités des PV
        updateDescription();
        updateCardCost();  // Mise à jour du coût de la carte
    }

    public void update() {
        // Si les PV du joueur changent pendant le tour, mettre à jour le coût de la carte
        if (this.amount != owner.currentHealth % 10) {
            onPlayerHealthChanged();
        }
    }
}
