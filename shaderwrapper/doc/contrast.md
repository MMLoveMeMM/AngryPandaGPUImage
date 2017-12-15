对比度
基本原理：
图像亮度本质上图像中每个像素的亮度，每个像素的亮度本质上RGB值的大小，RGB值为0
是像素点为黑色，RGB都为255时像素点最亮，为白色。对比度则是不同像素点之间的差值，
差值越大，对比度越明显。从直方图分析的观点来看，对比度越好的图片，直方图曲线会越
明显，分布也越显得均匀。
 
算法流程：
调整图像亮度与对比度算法主要由以下几个步骤组成：
1.      计算图像的RGB像素均值– M
2.      对图像的每个像素点Remove平均值-M : textureColor.rgb - vec3(0.5)
3.      对去掉平均值以后的像素点 P乘以对比度系数 : (textureColor.rgb - vec3(0.5)) * contrast
4.      对步骤上处理以后的像素P加上 M乘以亮度系统 : (textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)
5.      对像素点RGB值完成重新赋值
 
算法系数
对比度 contrast的最佳取值范围在[0 ~ 4],
亮度 brightness的最佳取值范围在[0~ 2]之间
算法的源程序代码见最后源代码部分

图像灰度化shader : 
public static final String CONTRAST_FRAGMENT_SHADER = "" +
            "varying highp vec2 textureCoordinate;\n" + 
            " \n" + 
            " uniform sampler2D inputImageTexture;\n" + 
            " uniform lowp float contrast;\n" + 
            " \n" + 
            " void main()\n" + 
            " {\n" + 
            "     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" + 
            "     \n" + 
            "     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n" + 
            " }";
            
            
  对比度应用 : 是图片亮的地方更亮,暗的地方更暗
  