package cn.zwz.data.utils;

import cn.zwz.data.vo.OssSettingVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@Tag(name = "文件配置接口类")
public interface FileManage {

    @Operation(description = "删除文件")
    void deleteFile(String key);

    @Operation(description = "重命名文件")
    String renameFile(String fromKey, String toKey);

    @Operation(description = "获取配置")
    OssSettingVo getOssSetting();

    @Operation(description = "拷贝文件")
    String copyFile(String fromKey, String toKey);

    @Operation(description = "文件流上传")
    String inputStreamUpload(InputStream inputStream, String key, MultipartFile file);
}
