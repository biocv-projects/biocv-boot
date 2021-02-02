package com.biocv.boot.protocol.sender;

import com.alibaba.fastjson.JSONObject;
import com.biocv.boot.protocol.ResultBean;
import com.biocv.boot.protocol.params.FingerprintBean;
import com.biocv.boot.protocol.params.UserParams;
import com.biocv.boot.protocol.params.Users;
import com.biocv.boot.protocol.provider.ProtocolProvider;
import io.netty.channel.ChannelHandlerContext;

/**
 * 下发人员信息指令
 * @author: amos.chen
 * @description:
 * @time: 2021/1/26  17:00
 */
public class PersonnelMsgSender implements ProtocolProvider {
    @Override
    public void process(ChannelHandlerContext ctx, JSONObject jsonObject) {
        UserParams userParams = new UserParams();
        Users user = new Users();
        FingerprintBean fingerprintBean = new FingerprintBean();
        fingerprintBean.setIndex("0");
        fingerprintBean.setTemplate("xxxxxxxxx");
        user.setPin("123456");
        user.setIdentityNumber("350623199810152051");
        user.setName("张三");
        user.setNation("汉族");
        user.setSex("男");
        user.setBirth("1996-10-15");
        user.setAddress("镇海路");
        user.setCard("3200378");
        user.setPhone("18755882211");
        user.setIdentityPhoto("1111111");
        user.setPhoto("1111111.jpg");
        user.setFingerprintBean(fingerprintBean);
        userParams.setUsers(user);
        ctx.writeAndFlush(ResultBean.sendSuccess("下发人员信息指令","cmd.update.user",userParams));


    }

    @Override
    public boolean supports(JSONObject jsonObject) {
        //TODO 不知已什么条件来确定进行这个下发请求，暂时采用循环发送所有下发命令
//        JSONObject payload = jsonObject.getJSONObject("payload");
//        JSONObject params = payload.getJSONObject("params");
//        Integer command = params.getInteger("command");
        return 1 == 1;
    }
}
