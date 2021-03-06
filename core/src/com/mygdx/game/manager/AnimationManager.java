package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ttwings on 17/1/4.
 */
public class AnimationManager {
    private AnimationManager() {
    }

    private static AnimationManager instance = new AnimationManager();

    public static AnimationManager getInstance() {
        return instance;
    }

    float frameDuration = 0.5f;
    public float dur = 0f;
    public int turn;
    public int ax,ay;
    TextureRegion[] keyFrames = new TextureRegion[0];
    Animation animation = new Animation(frameDuration, keyFrames);
    Cache cache = Cache.instance();
    Map<String,Animation> attackAniMap = new HashMap<>();
    public Animation getAttackAni(String file){
        Animation ani;
        dur = 0;
        if(attackAniMap.containsKey(file)){
               ani = attackAniMap.get(file);
        } else {
            ani = attack(file);
            attackAniMap.put(file,ani);
        }
        return ani;
    }

    public Animation attack(String file){
        Cache cache = Cache.instance();
        TextureRegion[][] tempRegion;
        TextureRegion[] keyFrames;
        Animation animation;

        int w,h,size;
        tempRegion = TextureRegion.split(cache.animation(file),192,192);
        w = tempRegion[0].length;
        h = tempRegion.length;
        size = w*h;
        keyFrames = new TextureRegion[size];
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                keyFrames[i*w+j] = tempRegion[i][j];
            }
        }
        animation = new Animation(0.05f,keyFrames);
//        dur = 0;
        return animation;
    }

    public void updata(){
//        仅仅是为了让 dur 不要无限度的增下去
        if(dur<10){
            dur = dur + Gdx.graphics.getDeltaTime();
        }
        if(turn<2000000000){
            turn ++;
        }else {
            turn =0;
        }
    }

    public TextureRegion moveFrame(TextureRegion[][] regions,EnumAction action,int turn){
        int index = turn%4;
        TextureRegion[] frames = new TextureRegion[4];
        if(action == EnumAction.MOVE_S){
            frames[0] = regions[0][0];
            frames[1] = regions[0][1];
            frames[2] = regions[0][2];
            frames[3] = regions[0][3];
        }if(action == EnumAction.MOVE_W){
            frames[0] = regions[1][0];
            frames[1] = regions[1][1];
            frames[2] = regions[1][2];
            frames[3] = regions[1][3];
        }if(action == EnumAction.MOVE_E){
            frames[0] = regions[2][0];
            frames[1] = regions[2][1];
            frames[2] = regions[2][2];
            frames[3] = regions[2][3];
        }if(action == EnumAction.MOVE_N){
            frames[0] = regions[3][0];
            frames[1] = regions[3][1];
            frames[2] = regions[3][2];
            frames[3] = regions[3][3];
        }

        return frames[index];
    }

}
