package bloodandhell.powers;

import bloodandhell.BasicMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

// CustomCard (BaseMod) et non AbstractCard : le constructeur d'AbstractCard cherche le chemin
// d'image dans l'atlas de cartes vanilla (où nos fichiers n'existent pas -> illustration "beta"
// par défaut) ; CustomCard charge le fichier du mod depuis le disque.
public class StrikeNow extends basemod.abstracts.CustomCard {
    public static final String ID = "StrikeNow";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("StrikeNow");

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public StrikeNow() {
        super("StrikeNow", cardStrings.NAME, BasicMod.imagePath("cards/attack/Strike.png"), -2, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);
        // CustomCard n'a pas l'équivalent setDamage(base, upgrade) de BaseCard : on initialise
        // les champs à la main, upgradeDamage() (AbstractCard) gère bien le delta à l'amélioration.
        this.baseDamage = this.damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        // Cette carte n'est jamais réellement posée en main (ChooseOneAction ne passe pas par
        // hand.applyPowers()), donc this.damage ne se recalculerait jamais tout seul : on force
        // le recalcul ici pour que la Force/Faiblesse actuelles soient bien prises en compte.
        this.applyPowers();
        addToTop(new DamageRandomEnemyAction(
                new DamageInfo(p, this.damage, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.FIRE
        ));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }

    public AbstractCard makeCopy() {
        return new StrikeNow();
    }
}
