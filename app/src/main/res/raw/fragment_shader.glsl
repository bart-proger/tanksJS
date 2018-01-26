//sprite

precision mediump float;

uniform sampler2D u_TextureUnit;
uniform mat4 u_TextureMatrix;
varying vec2 v_Texture;

void main()
{
    vec4 texcoord = u_TextureMatrix * vec4(v_Texture.x, v_Texture.y, 0, 1);
    gl_FragColor = texture2D(u_TextureUnit, texcoord.xy);
}
