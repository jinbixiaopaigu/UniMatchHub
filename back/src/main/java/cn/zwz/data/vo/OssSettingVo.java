package cn.zwz.data.vo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Tag(name = "文件存储配置VO类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssSettingVo {

    @Schema(description = "访问")
    private String fileView;

    @Schema(description = "http")
    private String fileHttp;

    @Schema(description = "路径")
    private String filePath;
}
