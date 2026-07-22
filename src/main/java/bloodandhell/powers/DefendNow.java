package bloodandhell.powers;

import bloodandhell.BasicMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

// CustomCard (BaseMod) et non AbstractCard : voir StrikeNow -- AbstractCard chercherait l'image
// dans l'atlas vanilla et retomberait sur l'illustration "beta".
public class DefendNow extends basemod.abstracts.CustomCard {
    public static final String ID = "DefendNow";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DefendNow");

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public DefendNow() {
        super("DefendNow", cardStrings.NAME, BasicMod.imagePath("cards/skill/Defend.png"), -2, cardStrings.DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        // CustomCard n'a pas l'équivalent setBlock(base, upgrade) de BaseCard : on initialise les
        // champs à la main, upgradeBlock() (AbstractCard) gère bien le delta à l'amélioration.
        this.baseBlock = this.block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        // Même raison que StrikeNow : force le recalcul de this.block via la Dextérité actuelle.
        this.applyPowers();
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }

    public AbstractCard makeCopy() {
        return new DefendNow();
    }
}
