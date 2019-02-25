package com.redeloock.loockantibot.skull;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public enum Skull {
	;

	private static final Base64 base64 = new Base64();
	public static ItemStack[] skullNum = new ItemStack[27];

	public static void SkullCarregar() {
		/*skullNum[0] = getCustomSkull("b04fa5ecbcc843807977221a1bb4b523a23cf518090f2a682af52d33e9b064");
		skullNum[1] = getCustomSkull("d837a6d222013db4f13bd9049b1d6ef1592508dda7057420b954726375ade1");
		skullNum[2] = getCustomSkull("e37b9d1d275e3e1e6f2adbe5a78389d26efed0bc2fdaebc27538a112a4acc77");
		skullNum[3] = getCustomSkull("bf7d31383a80838d79a8745a495d1f67d3766a2622cb5f3db4ac3992f7f415");
		skullNum[4] = getCustomSkull("b9ba8029b28254b6aef5397333138fa5d0abbf4c7dea9e667daed85873f45");
		skullNum[5] = getCustomSkull("d246ac67620d3b5217c49d9c30173a3793d4d41f4f251c9f6232eec75a74");
		skullNum[6] = getCustomSkull("16b7eb957bca6678cd0b63d84ea7bf28bf67eb8ab3f8f65beb461e236f429a");
		skullNum[7] = getCustomSkull("548782887434121a7cffd543d827282f73728851c4e3137dc3637f82b373a");
		skullNum[8] = getCustomSkull("bbf9a2e71692aa85d064baf06227fc1e5931a6827f3a6f7b7e38c279f5e75084");
		skullNum[9] = getCustomSkull("36d6161c6f04def6481e03b4c2972798816ddc514aef4b11c2d7a5a8fc9fe");
		skullNum[10] = getCustomSkull("388a2852ffbff2e495bdefad2fc2e63690375e5a1b6024e9c988e63965dd76de");
		skullNum[11] = getCustomSkull("bbba194423e8f5dad1dd6dcce2fc49d1494e902771112c416bbee648892e7a");
		skullNum[12] = getCustomSkull("f689792214bab52ba0cd29ff2377c4e763c28a90173e1d75cec58ddab73766");
		skullNum[13] = getCustomSkull("43ac8f8552e0ca49eaf837999a91768f499de6cbd954169e6aa660d794f32fbd");
		skullNum[14] = getCustomSkull("9179222906c5c949a52ea78456dcbacec39f6ab96740298b4f5021cbbfb89e");
		skullNum[15] = getCustomSkull("96d332d0da5d2fb231faa0e110cb668c1f64e96406af6351737d5b77c391e2");
		skullNum[16] = getCustomSkull("45ea9561eb3b8f785685ea383c62131316f8827f0decd3f0e99f182efc8cb");
		skullNum[17] = getCustomSkull("b4286b4581c57390b3a5ba2131b69cd9fdc8b815a969c1ed091cb78b38d837d");
		skullNum[18] = getCustomSkull("a56053c33ac7e3c16d2deef2feeb457a5fe886f5ba8e9f6a6aecf31e47906e72");
		skullNum[19] = getCustomSkull("4b6366cb3e39dc5b80bde56155eea47293911ca23e1ab4e2bb5e81da9b7244");
		skullNum[20] = getCustomSkull("4750a5463e89aa4936b679a16633815b152066749646e1667274d81e4aacc");
		skullNum[21] = getCustomSkull("b660eaccbe6fe25e8abd702a522788e318ed56562b115d6249a118d95f08a70");
		skullNum[22] = getCustomSkull("9dbf22c973d78845d075b9dc513f75e86d0a4a8b94abea85a9ee91752dbec1");
		skullNum[23] = getCustomSkull("a2e3c596fd645607ff2dc91122ea9c947f1dcf37ff2bc8c97b3a4fdeb023f");
		skullNum[24] = getCustomSkull("c96f4869cf4071cdaa667b102f4427ec19aed8f69d1740b14f2a0252fca1bb");
		skullNum[25] = getCustomSkull("fcb2ed02699eecc6b1f778ccf4c3fce7fcfcb98b6b9061e7c6befff3ea3de");
		skullNum[26] = getCustomSkull("8ff88b122ff92513c6a27b7f67cb3fea97439e078821d6861b74332a2396");*/
		for(int i = 0; i < 27; i++) {
			skullNum[i] = getCustomSkull("2fd253c4c6d66ed6694bec818aac1be7594a3dd8e59438d01cb76737f959");
		}
	}

	public static ItemStack getCustomSkull(String UUIDSkin) {
		String url = "http://textures.minecraft.net/texture/" + UUIDSkin;
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		PropertyMap propertyMap = profile.getProperties();
		if (propertyMap == null) {
			throw new IllegalStateException("Profile doesn't contain a property map");
		}
		byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		propertyMap.put("textures", new Property("textures", new String(encodedData)));
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta headMeta = head.getItemMeta();
		Class<?> headMetaClass = headMeta.getClass();
		Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
		head.setItemMeta(headMeta);
		return head;
	}
}