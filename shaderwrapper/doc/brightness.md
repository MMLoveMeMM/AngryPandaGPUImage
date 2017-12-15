亮度增强 :
图像亮度本质上图像中每个像素的亮度，每个像素的亮度本质上RGB值的大小，RGB值为0
是像素点为黑色，RGB都为255时像素点最亮，为白色.

算法原理 :
对R,G,B三通道分别增加brightness值即可

亮度shader :
public static final String BRIGHTNESS_FRAGMENT_SHADER = "" +
            "varying highp vec2 textureCoordinate;\n" +
            " \n" +
            " uniform sampler2D inputImageTexture;\n" +
            " uniform lowp float brightness;\n" +
            " \n" +
            " void main()\n" +
            " {\n" +
            "     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
            "     \n" +
            "     gl_FragColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n" +
            " }";