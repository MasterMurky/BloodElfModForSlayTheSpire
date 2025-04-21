package bloodandhell.cards.tempCards;

import basemod.AutoAdd;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
@AutoAdd.Ignore

public class SoldierDefend extends BaseCard {
    public static final String ID = makeID(SoldierDefend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 3;

    public SoldierDefend() {
        super(ID, info);
        setMagic(BLOCK, UPG_BLOCK); // utilise magicNumber comme block
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoldierDefend();
    }
}
