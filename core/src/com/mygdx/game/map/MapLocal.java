package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.ReadData;
import java.util.Map;

/**
 * 本地地图
 * @author ttwings
 * @version 0.1
 * @since 2013-12-30 下午3:46:30
 */
public class MapLocal {
//	id
	public String name;
	//	在区域中的位置，在世界地图中的位置
	public Vector2 posRegion;
	public Vector2 posWorld;
	//	区域宽，长，高，
	public int w, h, l;
	//	区域所处的气候带
	public String bolilmStr;
	//	区域现在的天气
	public String weatherStr;
	//	区域现在的气温
	public int temperature;
	//	区域现在的风力,风向
	public Vector2 wind2D;
	//	区域地形高度
	public int[][] level;
	//	room地图
	public String[][] rooms;
	public String[][] datas;
//	地图中所包含的人物列表
	public Map<String,BaseActor> actorMap;
//	地图中所包含的房间列表
	public Map<String,Room> roomMap;
//	事件列表,例如无量玉璧、钱塘观潮、金顶霞光等、当人物处在区域地图时，才进行判断
//	public Map<String,>
	public TiledMap tiledMap;
	public MapLocal(Vector2 posRegion, Vector2 posWorld, int w, int h, int l, String bolilmStr, String weatherStr, int temperature, Vector2 wind2D, int[][] level, String[][] datas) {
		this.posRegion = posRegion;
		this.posWorld = posWorld;
		this.w = w;
		this.h = h;
		this.l = l;
		this.bolilmStr = bolilmStr;
		this.weatherStr = weatherStr;
		this.temperature = temperature;
		this.wind2D = wind2D;
		this.level = level;
		this.datas = datas;
	}

	public MapLocal() {
		this.posRegion = new Vector2(0, 0);
		this.posWorld = new Vector2(0, 0);
		this.w = 0;
		this.h = 0;
		this.l = 0;
		this.bolilmStr = null;
		this.weatherStr = null;
		this.temperature = 0;
		this.wind2D = new Vector2(0, 0);
		this.level = null;
		this.datas = null;
	}

	public MapLocal(TiledMap tiledMap) {
		this.posRegion = new Vector2(0, 0);
		this.posWorld = new Vector2(0, 0);
		this.w = w;
		this.h = h;
		this.l = 0;
		this.bolilmStr = null;
		this.weatherStr = null;
		this.temperature = 0;
		this.wind2D = new Vector2(0, 0);
		this.level = new int[h][w];
		this.datas = new String[h][w];
		this.tiledMap = tiledMap;
	}
	public void readMatData(String file,int x,int y,int w,int h){
		String[][] matDatas = ReadData.readStrMat(file);
		String[][] tempMat = new String[w][h];
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				datas[i][j] = tempMat[i][j] = matDatas[y+i][x+j];
			}
		}
		this.w = datas[0].length;
		this.h = datas.length;
	}

	public void readMatDataSwapY(String file,int x,int y,int w,int h){
		String[][] matDatas = ReadData.readStrMatSwapY(file);
		String[][] tempMat = new String[w][h];
		for (int i=0;i<w;i++){
			for (int j=0;j<w;j++){
				datas[i][j] = tempMat[i][j] = matDatas[y+i][x+j];
			}
		}
		this.w = datas[0].length;
		this.h = datas.length;
	}
	public void setTiledMap(TiledMap map){
		this.tiledMap = map;
	}
	public TiledMap getTiledMap(){
		return this.tiledMap;
	}
	public void setName(String name){
		this.name = name;
	}
	public void removeObj(String objLayerName,String objName){
		MapLayer layer = tiledMap.getLayers().get(objLayerName);
		TiledMapTileMapObject tiledObject = null;
		for (MapObject object:layer.getObjects()) {
			tiledObject = (TiledMapTileMapObject) object;
			if (tiledObject.getName().equals(objName)) {
				layer.getObjects().remove(object);
			}
		}
	}
}