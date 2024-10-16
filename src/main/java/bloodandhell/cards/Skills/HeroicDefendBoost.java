package bloodandhell.cards.Skills;

import bloodandhell.cards.Skills.HeroicDefend;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class HeroicDefendBoost extends AbstractGameAction {
    private AbstractCard card;

    public HeroicDefendBoost(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    @Override
    public void update() {
        // Increase the block of the played HeroicDefend card
        this.card.baseBlock += this.amount;
        this.card.applyPowers();

        // Iterate over discard pile, draw pile, and hand to increase block of all HeroicDefend cards
        Iterator<AbstractCard> iterator;

        // Check discard pile
        iterator = AbstractDungeon.player.discardPile.group.iterator();
        while (iterator.hasNext()) {
            AbstractCard c = iterator.next();
            if (c instanceof HeroicDefend) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        // Check draw pile
        iterator = AbstractDungeon.player.drawPile.group.iterator();
        while (iterator.hasNext()) {
            AbstractCard c = iterator.next();
            if (c instanceof HeroicDefend) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        // Check hand
        iterator = AbstractDungeon.player.hand.group.iterator();
        while (iterator.hasNext()) {
            AbstractCard c = iterator.next();
            if (c instanceof HeroicDefend) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        this.isDone = true;
    }
}
