package com.yt.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yt.furn.bean.Furn;
import com.yt.furn.bean.Msg;
import com.yt.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Controller
public class FurnController {

    @Resource
    private FurnService furnService;

    /**
     * @param furn
     * @return com.yt.furn.bean.Msg
     * @RequestBody 将客户端中的数据封装成一个javaBean对象返回给服务端
     * @ResponseBody 会将服务端给客户端发送的数据转换为json字符串形式发送
     **/
    @PostMapping(value = "/save")
    @ResponseBody
    public Msg save(@Validated @RequestBody Furn furn, Errors errors) {

        HashMap<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        if (map.isEmpty()) {

            furnService.save(furn);
            return Msg.success();
        } else {
            return Msg.fail().add("errorMsg", map);
        }

    }

    @RequestMapping(value = "/furns")
    @ResponseBody
    public Msg listFurns() {
        List<Furn> furnList = furnService.findAll();
        Msg msg = Msg.success();
        msg.add("furnList", furnList);
        return msg;
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public Msg updateFurn(@RequestBody Furn furn) {
        furnService.update(furn);
        return Msg.success();
    }

    @DeleteMapping(value = "/del/{id}")
    @ResponseBody
    public Msg deleteFurn(@PathVariable Integer id) {
        furnService.delete(id);
        return Msg.success();
    }

    /**
     * 分页请求接口
     *
     * @param pageNum  要显示第几页
     * @param pageSize 每页要显示几条记录
     * @return com.yt.furn.bean.Msg
     * @RequestParam(defaultValue = "1")表示的是默认值
     **/
    @RequestMapping(value = "/furnsByPage")
    @ResponseBody
    public Msg listFurnByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize) {

        // 设置分页参数
        // 有了PageHelper.startPage(pageNum, pageSize); 之后 调用findAll是完成查询，底层会进行物理分页，而不是逻辑分页
        // 会根据分页参数计算 limit ?, ? , 在发出sql语句时，会带上limit
        PageHelper.startPage(pageNum, pageSize);
        List<Furn> furnList = furnService.findAll();

        // 将分页查询的结果，分装到PageInfo, 然后封装到Msg对象返回
        // pageInfo 包含了各个信息
        PageInfo<Furn> pageInfo = new PageInfo<>(furnList, pageSize);
        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 条件分页查询
     *
     * @param pageNum  要显示第几页
     * @param pageSize 每页要显示几条记录
     * @return com.yt.furn.bean.Msg
     * @RequestParam(defaultValue = "1")表示的是默认值
     **/
    @RequestMapping(value = "/furnsByConditionPage")
    @ResponseBody
    public Msg listFurnByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(defaultValue = "") String search) {

        // 设置分页参数
        // 有了PageHelper.startPage(pageNum, pageSize); 之后 调用findAll是完成查询，底层会进行物理分页，而不是逻辑分页
        // 会根据分页参数计算 limit ?, ? , 在发出sql语句时，会带上limit
        PageHelper.startPage(pageNum, pageSize);
        List<Furn> furnList = furnService.findByCondition(search);

        // 将分页查询的结果，分装到PageInfo, 然后封装到Msg对象返回
        // pageInfo 包含了各个信息
        PageInfo<Furn> pageInfo = new PageInfo<>(furnList, pageSize);
        return Msg.success().add("pageInfo", pageInfo);
    }
}

