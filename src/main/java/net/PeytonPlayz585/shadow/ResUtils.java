package net.PeytonPlayz585.shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFile2;
import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerFolderResourcePack;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

public class ResUtils {
    public static String[] collectFiles(String p_collectFiles_0_, String p_collectFiles_1_) {
        return collectFiles(new String[] {p_collectFiles_0_}, new String[] {p_collectFiles_1_});
    }

    public static String[] collectFiles(String[] p_collectFiles_0_, String[] p_collectFiles_1_) {
        Set<String> set = new LinkedHashSet<String>();
        IResourcePack[] airesourcepack = Config.getResourcePacks();

        for (int i = 0; i < airesourcepack.length; ++i) {
            IResourcePack iresourcepack = airesourcepack[i];
            String[] astring = collectFiles(iresourcepack, (String[])p_collectFiles_0_, (String[])p_collectFiles_1_, (String[])null);
            set.addAll(Arrays.<String>asList(astring));
        }

        String[] astring1 = (String[])set.toArray(new String[set.size()]);
        return astring1;
    }

    public static String[] collectFiles(IResourcePack p_collectFiles_0_, String p_collectFiles_1_, String p_collectFiles_2_, String[] p_collectFiles_3_) {
        return collectFiles(p_collectFiles_0_, new String[] {p_collectFiles_1_}, new String[] {p_collectFiles_2_}, p_collectFiles_3_);
    }

    public static String[] collectFiles(IResourcePack p_collectFiles_0_, String[] p_collectFiles_1_, String[] p_collectFiles_2_) {
        return collectFiles(p_collectFiles_0_, (String[])p_collectFiles_1_, (String[])p_collectFiles_2_, (String[])null);
    }

    public static String[] collectFiles(IResourcePack p_collectFiles_0_, String[] p_collectFiles_1_, String[] p_collectFiles_2_, String[] p_collectFiles_3_) {
        if (p_collectFiles_0_ instanceof DefaultResourcePack) {
            return collectFilesFixed(p_collectFiles_0_, p_collectFiles_3_);
        } else if (!(p_collectFiles_0_ instanceof AbstractResourcePack)) {
            return new String[0];
        } else {
            AbstractResourcePack abstractresourcepack = (AbstractResourcePack)p_collectFiles_0_;
            String file1 = abstractresourcepack.resourcePackFile;
            if(file1 == null) {
            	return new String[0];
            }
            String path = new VFile2(EaglerFolderResourcePack.RESOURCE_PACKS, file1).getPath();
            return collectFiles(new VFile2(path), "", p_collectFiles_1_, p_collectFiles_2_, file1);
        }
    }

    private static String[] collectFilesFixed(IResourcePack p_collectFilesFixed_0_, String[] p_collectFilesFixed_1_) {
        if (p_collectFilesFixed_1_ == null) {
            return new String[0];
        } else {
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < p_collectFilesFixed_1_.length; ++i) {
                String s = p_collectFilesFixed_1_[i];
                ResourceLocation resourcelocation = new ResourceLocation(s);

                if (p_collectFilesFixed_0_.resourceExists(resourcelocation)) {
                    list.add(s);
                }
            }

            String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
            return astring;
        }
    }

    private static String[] collectFiles(VFile2 p_collectFilesFolder_0_, String p_collectFilesFolder_1_, String[] p_collectFilesFolder_2_, String[] p_collectFilesFolder_3_, String texturePackName) {
        List<String> list = new ArrayList<String>();
        String s = "assets/minecraft/";
        List<String> afile = p_collectFilesFolder_0_.listFilenames(true);

        if (afile == null || afile.isEmpty()) {
            return new String[0];
        } else {
            for (int i = 0; i < afile.size(); ++i) {
            	VFile2 file1 = new VFile2(afile.get(i));
                if (file1.getPath().contains(".")) {
                    String s3 = p_collectFilesFolder_1_ + file1.getPath();
                    s3 = s3.replace("resourcepacks/", "");
                    s3 = s3.replace(texturePackName, "");
                    if(s3.startsWith("/")) {
                    	s3 = s3.substring(1);
                    }
                    if (s3.startsWith(s)) {
                        s3 = s3.substring(s.length());
                        if (StrUtils.startsWith(s3, p_collectFilesFolder_2_) && StrUtils.endsWith(s3, p_collectFilesFolder_3_)) {
                            list.add(s3);
                        }
                    }
                }
            }

            String[] astring1 = (String[])((String[])list.toArray(new String[list.size()]));
            return astring1;
        }
    }
}