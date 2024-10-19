package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;

public class WarTactics extends BaseCard {
    public static final String ID = makeID(WarTactics.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );


    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;


    public WarTactics() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
            }

    public WarTactics(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, BLOCK));
        addToBot((AbstractGameAction)new DiscardPileToTopOfDeckAction((AbstractCreature)p));
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new WarTactics();
    }
}
