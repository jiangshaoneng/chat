package com.jiang.chat;

import com.jiang.chat.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 18:19
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class nettyBooter implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            try {
                WSServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}