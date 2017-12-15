饱和度（Saturation）的定义 :
饱和度是指某一颜色的“纯度”，或者说，它离纯灰色的距离。
当饱和度降低时，该颜色更接近灰色；反之则更纯、更鲜艳.

算法思路：计算每个片元（只有绘制的帧缓冲区大小为原图大小时才有一个片元对应一个像素）的亮度值，
以亮度值为基础，根据外部设置的saturation值为系数进行亮度与原始颜色的线性混合。根据mix函数说明
mix(x, y, a) -> x * (1 - a) + y * a
则最终计算为:
final_color = luminance(x, y) * (1 - saturation) + rgb(x, y) * saturation

饱和度shader程序:
varying highp vec2 textureCoordinate;

uniform sampler2D inputImageTexture;
uniform lowp float saturation;

const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);

void main()
{
    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);
    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);
    lowp vec3 greyScaleColor = vec3(luminance); // 算出了图片在该点的灰度值
    
    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);
}

结果分析:
saturation的变化范围为[0, 2]，saturation值越小，颜色越趋近灰色，反之则越鲜艳。
0表示完全欠饱和，1为原始颜色值，2表示最大饱和度.

