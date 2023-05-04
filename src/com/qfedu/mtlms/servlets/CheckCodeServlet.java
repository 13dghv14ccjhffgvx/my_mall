package com.qfedu.mtlms.servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Description  生产验证码图片
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过  awt提供的类绘制验证码图片
        //1.创建一张图片
        int width = 300;  //验证码宽度
        int height = 90;  //验证码高度
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.绘制图片 , 从图片对象中获取绘制图片的 “笔”
        Graphics2D pen = image.createGraphics();
        //a.绘制背景
        pen.setColor(getRandomColor());
        //fillRect: 绘制实心矩形
        pen.fillRect(0,0,width,height);

        //b.绘制验证码字符串
        int letterNum = 4;  //验证码图片上的字符的个数
        int space = 20; // 验证码图片上两个字母之间的空隙
        int letterWidth = ( width-(letterNum+1)*space )/letterNum; //计算每个字母占据的宽度

        //for循环每循环一次，绘制一个字母  （小写字母的ascii码  97-122）
        String code = "";
        Random random = new Random();
        for(int i=0; i<letterNum ; i++){
            //随机生成一个小写字母：
            int ascii = random.nextInt(26) + 97; //97-122
            byte[] bs = {(byte)ascii};
            String letter = new String(bs);
            code = code+letter;
            //drawString: 绘制字母
            pen.setColor(getRandomColor());
            pen.setFont( new Font("Gulim",Font.BOLD,70) );
            pen.drawString(letter, space+(letterWidth+space)*i,height-space);
        }
        HttpSession session = request.getSession();
        session.setAttribute("code",code);

        // 为了让图片不是那么容易被程序识别，可以绘制干扰线、干扰图形

        //图片绘制完成之后，将图片通过 response的输出流响应到客户端
        ImageIO.write(image,"png",response.getOutputStream());

    }

    /**
     *  产生一种随机颜色
     */
    private Color getRandomColor(){
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color color = new Color(r, g, b);
        return color;
    }
}
