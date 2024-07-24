package com.link.blog.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description 逻辑恢复或删除请求
 * @date 2024-07-22 21:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteRequest {

    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    private List<Integer> idList;

    /**
     * 状态值
     */
    @NotNull(message = "状态值不能为空")
    private Integer isDelete;

}
