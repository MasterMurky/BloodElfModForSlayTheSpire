package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HeroicDefend extends BaseCard {
    public static final String ID = makeID(HeroicDefend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER
            CardRarity.COMMON, //Rarity.
            CardTarget.SELF, //The target is SELF since it's a defensive card.
            1 //The card's base cost.
    );

    private static final int BLOCK = 0; // Initial block is 0.
    private static final int UPGRADE_BLOCK = 1; // Upgrade increase for block scaling.

    public HeroicDefend() {
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = 4; // The amount of block increase per play.
        setBlock(BLOCK);
        setMagic(magicNumber, UPGRADE_BLOCK); // Use magicNumber for block scaling.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new HeroicDefendBoost(this, this.magicNumber)); // Apply block boost each time it's played.
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeroicDefend();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_BLOCK); // Block scaling increases when upgraded.
        }
    }
}
