package org.cheng.seckill.web;

import org.cheng.seckill.dto.Exposer;
import org.cheng.seckill.dto.SeckillExecution;
import org.cheng.seckill.dto.SeckillResult;
import org.cheng.seckill.entity.Seckill;
import org.cheng.seckill.enums.SeckillStatEnum;
import org.cheng.seckill.exception.RepeatKillException;
import org.cheng.seckill.exception.SeckillCloseException;
import org.cheng.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by cheng
 * 2017/8/27 19:48
 */
@Controller
@RequestMapping("/seckill")//url:/模块/资源/{id}/细分
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/list")
    public String list(Model model) {
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @GetMapping("/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    //produces:请求编码
    @PostMapping(value = "/{seckillId}/exposer", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exporser(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/{seckillId}/{md5}/execution", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, exception);
        } catch (SeckillCloseException e) {
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, exception);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExecution exception = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, exception);
        }
    }

    @GetMapping("/time/now")
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
