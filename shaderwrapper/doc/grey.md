图像灰度化算法:
 什么叫灰度图？任何颜色都有红、绿、蓝三原色组成，假如原来某点的颜色为RGB(R，G，B)，那么，我们可以通过下面几种方法，将其转换为灰度： 　　
   1.浮点算法：Gray=R*0.3+G*0.59+B*0.11 　　
   2.整数方法：Gray=(R*30+G*59+B*11)/100 　　
  3.移位方法：Gray =(R*28+G*151+B*77)>>8; 　　
   4.平均值法：Gray=（R+G+B）/3; 　　
   5.仅取绿色：Gray=G； 　　
    通过上述任一种方法求得Gray后，将原来的RGB(R,G,B)中的R,G,B统一用Gray替换，形成新的颜色RGB(Gray,Gray,Gray)，用它替换原来的RGB(R,G,B)就是灰度图了。
灰度分为256阶。所以，用灰度表示的图像称作灰度图


图像灰度化shader:
public static final String GRAYSCALE_FRAGMENT_SHADER = "" +
            "precision highp float;\n" +
            "\n" +
            "varying vec2 textureCoordinate;\n" +
            "\n" +
            "uniform sampler2D inputImageTexture;\n" +
            "\n" +
            "const highp vec3 W = vec3(0.2125, 0.7154, 0.0721);\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "  lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
            "  float luminance = dot(textureColor.rgb, W);\n" +
            "\n" +
            "  gl_FragColor = vec4(vec3(luminance), textureColor.a);\n" +
            "}";