//sprite

attribute vec4 a_Position;
attribute vec2 a_Texture;

uniform mat4 u_ProjectionViewMatrix;
uniform mat4 u_ModelMatrix;

varying vec2 v_Texture;

void main()
{
    gl_Position = u_ProjectionViewMatrix * u_ModelMatrix * a_Position;
    v_Texture = a_Texture;
}
