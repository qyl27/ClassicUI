package cx.rain.classicui.ninepatch;

import com.mojang.blaze3d.platform.NativeImage;
import cx.rain.classicui.utility.DebugLoggerHelper;
import cx.rain.classicui.utility.Location;
import cx.rain.classicui.utility.Size;
import cx.rain.classicui.utility.UV;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

import java.io.IOException;

public final class NinePatch {
    public static final int FULL_ALPHA = 255;
    public static final int BLACK_COLOR = FastColor.ARGB32.color(FULL_ALPHA, 0, 0, 0);

    private ResourceLocation imageLocation;
    private int imageWidth;
    private int imageHeight;
    private Location imagePatchStart;
    private Size imagePatchSize;
    private Location imagePaddingStart;
    private Size imagePaddingSize;

    /**
     * Create NinePatch instance for image.
     * @param image ResourceLocation of the image.
     * @param width Image width.
     * @param height Image height.
     * @param patchStart Patch start location.
     * @param patchSize Patch size.
     * @param paddingStart Padding start location.
     * @param paddingSize Padding size.
     */
    private NinePatch(ResourceLocation image, int width, int height,
                      Location patchStart, Size patchSize,
                      Location paddingStart, Size paddingSize) {
        imageLocation = image;
        imageWidth = width;
        imageHeight = height;
        imagePatchStart = patchStart;
        imagePatchSize = patchSize;
        imagePaddingStart = paddingStart;
        imagePaddingSize = paddingSize;
    }

    public static NinePatch from(ResourceLocation image) {
        var resource = Minecraft.getInstance().getResourceManager().getResource(image).orElse(null);

        if (resource == null) {
            DebugLoggerHelper.error("No resource found in: " + image);
            return null;
        }

        try {
            var origin = NativeImage.read(NativeImage.Format.RGBA, resource.open());

            // Todo: qyl27: test it.
            // Read first row.
            var flag = false;
            var patchWidthStart = 0;
            var patchWidthEnd = 0;
            for (var i = 0; i < origin.getWidth(); i++) {
                var pixel = origin.getPixelRGBA(i, 0);
                var alpha = FastColor.ARGB32.alpha(pixel);
                if (alpha == FULL_ALPHA) {
                    if (!flag) {
                        flag = true;
                        patchWidthStart = i;
                    }
                } else {
                    if (flag) {
                        patchWidthEnd = i - 1;
                    }
                }
            }

            // Read first column.
            flag = false;
            var patchHeightStart = 0;
            var patchHeightEnd = 0;
            for (var i = 0; i < origin.getHeight(); i++) {
                var pixel = origin.getPixelRGBA(0, i);
                var alpha = FastColor.ARGB32.alpha(pixel);
                if (alpha == FULL_ALPHA) {
                    if (!flag) {
                        flag = true;
                        patchHeightStart = i;
                    }
                } else {
                    if (flag) {
                        patchHeightEnd = i - 1;
                    }
                }
            }

            // Read last row.
            var paddingWidthStart = 0;
            var paddingWidthEnd = 0;
            for (var i = 0; i < origin.getWidth(); i++) {
                var pixel = origin.getPixelRGBA(i, origin.getHeight() - 1);
                var alpha = FastColor.ARGB32.alpha(pixel);
                if (alpha == FULL_ALPHA) {
                    if (!flag) {
                        flag = true;
                        paddingWidthStart = i;
                    }
                } else {
                    if (flag) {
                        paddingWidthEnd = i - 1;
                    }
                }
            }

            // Read last column.
            var paddingHeightStart = 0;
            var paddingHeightEnd = 0;
            for (var i = 0; i < origin.getHeight(); i++) {
                var pixel = origin.getPixelRGBA(origin.getWidth() - 1, i);
                var alpha = FastColor.ARGB32.alpha(pixel);
                if (alpha == FULL_ALPHA) {
                    if (!flag) {
                        flag = true;
                        paddingHeightStart = i;
                    }
                } else {
                    if (flag) {
                        paddingHeightEnd = i - 1;
                    }
                }
            }

            var patchStart = new Location(patchWidthStart, patchHeightStart);
            var patchSize = new Size(patchWidthEnd - patchWidthStart, patchHeightEnd - patchWidthStart);
            var paddingStart = new Location(paddingWidthStart, paddingHeightStart);
            var paddingSize = new Size(paddingWidthEnd - paddingWidthStart, paddingHeightEnd - paddingWidthStart);

            return new NinePatch(image, origin.getWidth(), origin.getHeight(), patchStart, patchSize, paddingStart, paddingSize);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Get UV array with ⑨ png.
     * <p />
     * The returns array contains 4 * 9 = 36 elements, <br />
     * 0 - 3: Top left part uvs; <br />
     * 4 - 7: Top center part uvs; <br />
     * 8 - 11: Top right part uvs; <br />
     * 12 - 15: Middle left part uvs; <br />
     * 16 - 19: Middle center part uvs; <br />
     * 20 - 23: Middle right part uvs; <br />
     * 24 - 27: Bottom left part uvs; <br />
     * 28 - 31: Bottom center part uvs; <br />
     * 32 - 35: Bottom right part uvs.
     * <p />
     * In every 0 - 3 element, <br />
     * 0: Top left point uv; <br />
     * 1: Top right point uv; <br />
     * 2: Bottom left point uv; <br />
     * 3: Bottom right point uv.
     *
     * @return UV array.
     */
    public UV[] getUV() {
        var patchEnd = new Location(imagePatchStart.x() + imagePatchSize.width(), imagePatchStart.y() + imagePatchSize.height());
        var paddingEnd = new Location(imagePaddingStart.x() + imagePaddingSize.width(), imagePaddingStart.y() + imagePaddingSize.height());

        var result = new UV[4 * 9];
        result[0] = new UV(clipWidth(imagePatchStart.x()), clipHeight(imagePatchStart.y()));
        result[1] = new UV(clipWidth(imagePaddingStart.x()), clipHeight(imagePatchStart.y()));
        result[2] = new UV(clipWidth(imagePatchStart.x()), clipHeight(imagePaddingStart.y()));
        result[3] = new UV(clipWidth(imagePaddingStart.x()), clipHeight(imagePaddingStart.y()));

        result[4] = result[1];
        result[5] = new UV(clipWidth(paddingEnd.x()), clipHeight(imagePatchStart.y()));
        result[6] = result[3];
        result[7] = new UV(clipWidth(paddingEnd.x()), clipHeight(imagePaddingStart.y()));

        result[8] = result[5];
        result[9] = new UV(clipWidth(patchEnd.x()), clipHeight(imagePatchStart.y()));
        result[10] = result[7];
        result[11] = new UV(clipWidth(patchEnd.x()), clipHeight(imagePaddingStart.y()));

        result[12] = result[2];
        result[13] = result[3];
        result[14] = new UV(clipWidth(imagePatchStart.x()), clipHeight(paddingEnd.y()));
        result[15] = new UV(clipWidth(imagePaddingStart.x()), clipHeight(paddingEnd.y()));

        result[16] = result[3];
        result[17] = result[7];
        result[18] = result[15];
        result[19] = new UV(clipWidth(paddingEnd.x()), clipHeight(paddingEnd.y()));

        result[20] = result[7];
        result[21] = result[11];
        result[22] = result[19];
        result[23] = new UV(clipWidth(patchEnd.x()), clipHeight(paddingEnd.y()));

        result[24] = result[14];
        result[25] = result[15];
        result[26] = new UV(clipWidth(imagePatchStart.x()), clipHeight((patchEnd.y())));
        result[27] = new UV(clipWidth(imagePaddingStart.x()), clipHeight((patchEnd.y())));

        result[28] = result[15];
        result[29] = result[19];
        result[30] = result[27];
        result[31] = new UV(clipWidth(paddingEnd.x()), clipHeight((patchEnd.y())));

        result[32] = result[19];
        result[33] = result[23];
        result[34] = result[31];
        result[35] = new UV(clipWidth(patchEnd.x()), clipHeight((patchEnd.y())));

        return result;
    }

    private float clipWidth(int i) {
        return 1.0f / imageWidth * i;
    }

    private float clipHeight(int i) {
        return 1.0f / imageHeight * i;
    }
}
