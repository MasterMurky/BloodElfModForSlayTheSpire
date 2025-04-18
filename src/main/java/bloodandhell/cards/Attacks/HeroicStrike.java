package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;

public class HeroicStrike extends BaseCard {
    public static final String ID = makeID(HeroicStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    private static final int DAMAGE = 0;
    private static final int upg_magicNumber = 1; //l'UPG du MN va ici mais le MN va dans la méthode juste en dessous


    public HeroicStrike() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.magicNumber = this.baseMagicNumber = 5;

        setDamage(DAMAGE);
        setMagic(magicNumber, upg_magicNumber);
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects


    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        if (m != null)
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new ClawEffect(m.hb.cX, m.hb.cY, Color.PURPLE, Color.WHITE), 0.1F)); //L'animation visuelle

        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL))); //(1er paramètre = la cible à laquelle infliger des dégâts), DamageInfo (source des dégâts, amount et type de dégâts)
        addToBot(new HeroicStrikeBOOST(this, this.magicNumber));
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new HeroicStrike();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(upg_magicNumber); //on utilise upg_MN à la place de MG lorsque la carte est améliorée
        }
    }
}