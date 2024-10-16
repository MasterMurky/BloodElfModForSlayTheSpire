package bloodandhell.cards.Powers;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;

public class EternalAegis extends BaseCard {
    public static final String ID = makeID(EternalAegis.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int upg_magicNumber = 1; //l'UPG du MN va ici mais le MN va dans la méthode juste en dessous


    public EternalAegis() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.magicNumber = this.baseMagicNumber = 2;
        setCustomVar("HPLost", 7, -2); //Setting a custom variable with a base value of 7, decrease by 2 on upgrade


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        addToBot(new LoseHPAction(p,p, customVar("HPLost")));
        addToBot(new ApplyPowerAction(p,p,new BufferPower(p, this.magicNumber), this.magicNumber));                                                                                                                                                //types de dégâts : NORMAL, THORNS, and HP_LOSS. Attacks deal NORMAL damage. Any blockable damage that isn't from an attack is THORNS damage (such as from Thorns). Damage that ignores block is HP_LOSS.
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new EternalAegis();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(upg_magicNumber); //on utilise upg_MN à la place de MG lorsque la carte est améliorée
            //faut que la carte baisse les PV perdus de 2 lors de l'upgrade --> ou pas eft
        }
    }
}