package com.lq.redis;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author qliu84
 * @version 1.0
 * @date 2019/12/10 上午 02:55
 */
@Controller
public class PlanCnsMaterialPlanStatusController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("plan_cns_material_plan_status")
    @ResponseBody
    public String addRecord(String key, String value) {
        redisUtil.hset("/plan/cns_material_plan_status", key, value);
        return "added";
    }

    @RequestMapping("plan_cns_material_plan_status_one")
    @ResponseBody
    public String addRecord(String key) {
        String value = key;
        System.out.println(key);
        redisUtil.hset("/plan/cns_material_plan_status", key, value);
        return "added";
    }

    @RequestMapping("plan_cns_material_plan_status_multi")
    @ResponseBody
    public boolean addRecord(String localMaterialNumber, String localPlant, String sourceSystem) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("localMaterialNumber", localMaterialNumber);
            jsonObject.put("localPlant", localPlant);
            jsonObject.put("sourceSystem", sourceSystem);
            String key = jsonObject.toString();
            String value = key;
            System.out.println(key);
            return redisUtil.hset("/plan/cns_material_plan_status", key, value);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("plan_cns_material_plan_status_add")
    @ResponseBody
    public boolean addRecord(Map<String, String> map) {

        try {
            JSONObject jsonObject = new JSONObject(map);
            String key = jsonObject.toString();

            jsonObject.put("value1", "value1");
            String value = jsonObject.toString();

            System.out.println(key);
            System.out.println(value);

            return redisUtil.hset("/plan/cns_material_plan_status", key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping("plan_cns_material_plan_status_del")
    @ResponseBody
    public void delRecord(String key) {
        redisUtil.hdel("/plan/cns_material_plan_status", key);
    }

}
