package net.PeytonPlayz585.shadow.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.IMetadataSection;

public class JSONUtils {

	public static <T extends IMetadataSection> T parseCustomItemAnimation(String string) {
		JSONObject jsonObject = new JSONObject(string);
		JSONObject animationObject = jsonObject.getJSONObject("animation");
		int frametime = animationObject.getInt("frametime");
		JSONArray frames = animationObject.getJSONArray("frames");
		boolean interpolate = false;
		
		if(animationObject.has("interpolate")) {
			interpolate = animationObject.getBoolean("interpolate");
		}
		
		List<AnimationFrame> list = new ArrayList<AnimationFrame>();
		
		for(int i = 0; i < frames.length(); i++) {
			int time;
			if(frames.get(i) instanceof JSONObject) {
				JSONObject obj = frames.getJSONObject(i);
				if(obj.has("time")) {
					time = obj.getInt("time");
				} else {
					time = -1;
				}
			} else {
				time = -1;
			}
			list.add(new AnimationFrame(i, time));
		}
		
		AnimationMetadataSection data = new AnimationMetadataSection(list, 16, 16, frametime, interpolate);
		IMetadataSection imetadatasection = (IMetadataSection) data;
		return (T) imetadatasection;
	}
}
