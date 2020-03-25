##### Mybatis开发注意事项  

- update语句中的，是需要自行添加的， 如  
```xml
    <update id="updateMaterial">
        update t_material
        <trim prefix="set" suffixOverrides=",">
            <if test='name!=null'>NAME=#{name},</if>
            <if test='capacity!=null'>CAPACITY=#{capacity},</if>
            <if test='unit!=null'>UNIT=#{unit},</if>
            <if test='price!=null'>PRICE=#{price},</if>
            <if test='pricePerUnit!=null'>PRICE_PER_UNIT=#{pricePerUnit},</if>
        </trim>
        where id = #{id}
    </update>
```