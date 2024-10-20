package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

public class SacrificialBlade extends BaseCard {
    public static final String ID = makeID(SacrificialBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 4;


    public SacrificialBlade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.magicNumber = this.baseMagicNumber = 1;

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        if(m!=null)
            addToBot((AbstractGameAction)new VFXAction(new HemokinesisEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        addToBot(new LoseHPAction(p,p, 1));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY)); //(1er paramètre = la cible à laquelle infliger des dégâts), DamageInfo (source des dégâts, amount et type de dégâts)
                                                                                                                                                //types de dégâts : NORMAL, THORNS, and HP_LOSS. Attacks deal NORMAL damage. Any blockable damage that isn't from an attack is THORNS damage (such as from Thorns). Damage that ignores block is HP_LOSS.
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new SacrificialBlade();
    }
}
