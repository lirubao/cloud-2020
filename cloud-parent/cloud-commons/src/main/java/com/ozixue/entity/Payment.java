package com.ozixue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    private static final long serialVersionUID = 4757092515067501120L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String serial;
}
