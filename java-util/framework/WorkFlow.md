Bob -> Alice : Hello, how are you
Alice -> Bob : Fine, thank you, and you?

@startuml

title 时序图

== 鉴权阶段 ==

Alice -> Bob: 请求
Bob -> Alice: 应答

== 数据上传 ==

Alice -> Bob: 上传数据
note left: 这是显示在左边的备注

Bob --> Canny: 转交数据
... 不超过 5 秒钟 ...
Canny --> Bob: 状态返回
note right: 这是显示在右边的备注

Bob -> Alice: 状态返回

== 状态显示 ==

Alice -> Alice: 给自己发消息

@enduml


@startuml

title 业务日志系统时序图

业务操作 -> 消息队列: 发送消息
== 数据处理模块 ==

消费者 -> 消息队列: 消费消息
消费者 -> ES: 写数据
消费者 -> MySql: 写数据

== 对外服务模块 ==
日志查询服务 -> ES: 读取数据
运营 -> 日志查询服务: 查询数据
其他业务系统 -> 日志查询服务: 查询数据
@enduml





@startuml

left to right direction
actor 商户
actor 运营
actor 下游业务系统
商户 -- (arena.log_consumer) : 发送业务变更消息
(arena.log_consumer) .> (数据持久化) : 数据写入
(数据持久化)  .> (arena.log_service) : 数据读取
(arena.log_service)  .> (arena.log_api) :soa接口
(arena.log_api) -- 运营 :查询
(arena.log_service)  -- 下游业务系统 :soa接口
@enduml


@startuml

left to right direction
actor 消费者
actor 销售员
rectangle 买单 {
消费者 -- (买单)
(买单) .> (付款) : include
(帮助) .> (买单) : extends
(买单) -- 销售员
}

@enduml

(业务变更) .> （日志消息服务arena.log_consumer）: include
(日志消息服务arena.log_consumer).> 持久化DB
(日志查询service) .> (日志消费服务)

关于持久化mysql还是ekv，找DBA对了一下，如果用mysql的话写的tps DBA不建议超过7k,用EKV的话，能支持更高的写入。
所以需要先调研，到底是





@startuml
title 生成session
start
:uuid=生成36位16进制uuid
:identifierRaw = uuid取base64
:identifier = identifierRaw.sbuString(0, 30)
:verifierRaw = （identifier + secret).md5
:timeStampHex = System.currentTimeStamp转换位16进制
:sessionId = identifier + verifier + timeStampHex
return sessionId
@enduml




@startuml
title 新建账号逻辑
start
if(判断用户名是否存在) then(true)
	:创建失败，提示“该用户名已存在”;
else
	if(判断手机号是否被使用) then(true)
		:创建失败，提示"该手机号已经被使用";
	else
		if(邮箱是否被使用) then(true)
			:创建失败，提示“该邮箱已被使用”;
		else
			if(该店铺是否已有管理员) then(true)
				:创建失败，提示“该店铺已有管理员";
			else
				:调用eus接口，新建账号;
				:写入napos_keeper库;
				:绑定账号店铺关系;
				:初始化角色;
				:创建成功，返回账号Id;
			endif
		endif
	endif
endif
stop
@enduml





```
@startuml
actor clients
actor operators
actor queue
rectangle modules {
    clients -- (modules):get
    (modules) .> (configs) : include
    (modules) .> (dynamicData) : include
    (permissions) .> (modules):impact
    (requestVesion) .> (modules):impact
    (modules) -- queue:listen
    (modules) -- operators:set
}

@enduml
```

```
@startuml
actor clients
actor operators
actor queue
rectangle modules {
    clients -- (modules):get
    (modules) .> (configs) : include
    (modules) .> (dynamicData) : include
    (permissions) .> (modules):impact
    (requestVesion) .> (modules):impact
    (modules) -- queue:listen
    (modules) -- operators:set
}

@enduml
```

```
@startuml
actor clients
actor operators
actor queue
rectangle modules {
    clients -- (modules):get
    (modules) .> (configs) : include
    (modules) .> (dynamicData) : include
    (permissions) .> (modules):impact
    (requestVesion) .> (modules):impact
    (modules) -- queue:listen
    (modules) -- operators:set
}

@enduml
```

```
@startuml

title 动态布局容器
== 客户端请求 ==

Client -> Layout:请求获取模块配置
note right:shopId,keeperId,requestVersion

== 数据拉取 ==

Layout -> Storage:获取configs（shop/city/all）
note right:redis缓存半小时
Storage -> Layout:module configs

== 鉴权 ==

Layout -> Keeper:获取权限列表
Keeper -> Layout:permission codes

== 响应 ==

Layout -> Client: module configs
note left:匹配permission codes和\n相应请求版本的可见模块配置（版本暂未实现）

== dynamicData ==
Layout --> Queue:listen
note right:arena.layout.invoice
Queue --> Layout:红点

@enduml


@startuml


interface ModuleGroupConfigService
interface ModuleDetailConfigService
interface ShopModuleVisibleService
interface DynamicDataHandlerService
interface Dao
annotation ConsumerHandler
annotation MessageMapping


class ModuleGroupConfigServiceImpl
class ModuleDetailConfigServiceImpl
class ShopModuleVisibleServiceImpl

class ModuleGroupConfigHelper
class ModuleGroupConfigServiceHelp
class PModuleGroupConfigDao
class ModuleGroupConfig
class ModuleConfig
class ModuleGroupConfigDTO
class ModuleDetailConfigServiceHelper
class LayoutCache
class ModuleDetailConfigHelper
class PModuleDetailConfigDao
class ShopModuleVisibleHelper
class CommentServiceHelper
class DynamicModuleNamsSwitch
class RestaurantService
class KeeperService
class PShopModuleVisibleDao
class ShopModuleVisible
class ModuleDetailConfigDTO



ModuleGroupConfigServiceImpl <|.. ModuleGroupConfigService
PModuleGroupConfigDao <|.. Dao
PModuleDetailConfigDao <|.. Dao
PShopModuleVisibleDao <|.. Dao
ModuleDetailConfigServiceImpl <|.. ModuleDetailConfigService
ShopModuleVisibleServiceImpl <|.. ShopModuleVisibleService
ModuleGroupConfigServiceImpl .up.> ModuleGroupConfig
ModuleGroupConfigServiceImpl .up.> ModuleGroupConfigDTO
ModuleGroupConfigServiceImpl .up.> ModuleGroupConfigHelper
ModuleGroupConfigServiceImpl .up.> ModuleGroupConfigServiceHelp
ModuleGroupConfigHelper .up.> PModuleGroupConfigDao
ModuleGroupConfigServiceHelp .up.> ModuleGroupConfigHelp
ModuleGroupConfigServiceHelp .up.> ModuleDetailConfigServiceHelper
ModuleGroupConfigServiceHelp .up.> LayoutCache
ModuleDetailConfigServiceHelper .up.> ModuleDetailConfigHelper
ModuleDetailConfigServiceHelper .up.> ShopModuleVisibleHelper
ModuleDetailConfigServiceHelper .up.> CommentServiceHelper
ModuleDetailConfigServiceHelper .up.> RestaurantService
ModuleDetailConfigServiceHelper .up.> KeeperService
ModuleDetailConfigServiceHelper .up.> DynamicModuleNamsSwitch
ModuleDetailConfigServiceHelper .up.> LayoutCache
PShopModuleVisibleDao <.down. ShopModuleVisibleHelper
ShopModuleVisibleServiceImpl .up.> ShopModuleVisible
ModuleDetailConfigServiceImpl .up.> ModuleDetailConfigHelper
ModuleDetailConfigServiceImpl .up.> ModuleDetailConfigServiceHelper
ModuleDetailConfigServiceImpl .up.> ModuleDetailConfigDTO
ModuleDetailConfigServiceImpl .up.> ModuleConfig
DynamicDataHandlerService .up.> ConsumerHandler:消费北研发票小红点消息
DynamicDataHandlerService .up.> MessageMapping





interface ModuleGroupConfigService {

    List<ModuleGroupConfig> getAppModuleGroupConfigs(final int shopId, final int keeperId, String requestVersion) throws ServiceException;
    List<ModuleGroupConfig> getPcModuleGroupConfigs(final int shopId, final int keeperId) throws ServiceException;
    ModuleGroupConfigDTO createModuleGroupConfig(ModuleGroupConfigDTO moduleGroupConfigDTO) throws ServiceException;
    ModuleGroupConfigDTO updateModuleGroupConfig(ModuleGroupConfigDTO moduleGroupConfigDTO) throws ServiceException;
    ModuleGroupConfigDTO inValidModuleGroup(final int moduleGroupId) throws ServiceException;
    List<ModuleGroupConfigDTO> getModuleGroupConfigsByPlatform(String platform) throws ServiceException;

}


class ModuleGroupConfig {

    private int groupId;
    private String groupName;
    private String groupImgUrl;
    private String platform;
    private int showGroup;
    private int groupSequence;
    private List<ModuleConfig> moduleConfigs = Lists.newArrayList();
}

class ModuleGroupConfigDTO {

    private int groupId;
    private String groupName;
    private String groupImgUrl;
    private String platform;
    private int showGroup;
    private int groupSequence;

}

class ModuleGroupConfigServiceHelp{
public List<ModuleGroupConfig> getModuleGroupConfigs(int shopId, int keeperId, String requestVersion, String platform) throws ServiceException
}

class ModuleDetailConfigServiceHelper {
    public ModuleDetailConfigDTO inValidModuleConfig(int moduleId) throws ServiceException
    public List<String> getModuleNamesByPlatform(@NotNull String platform)
    public List<ModuleConfig> getModuleConfigsWithPermission(int shopId, int keeperId, String requestVersion, String platform) throws ServiceException
    }

interface ModuleDetailConfigService {

    List<ModuleDetailConfigDTO> getModuleDetailConfigs(String platform) throws ServiceException;

    List<ModuleConfig> getModuleConfigsWithPermission(int shopId, int keeperId, String requestVersion, String platform) throws ServiceException;

    ModuleDetailConfigDTO getModuleDetailConfigById(final int id) throws ServiceException;

    ModuleDetailConfigDTO createModuleDetailConfig(ModuleDetailConfigDTO moduleDetailConfigDTO) throws ServiceException;

    ModuleDetailConfigDTO updateModuleDetailConfig(ModuleDetailConfigDTO moduleDetailConfigDTO) throws ServiceException;

    ModuleDetailConfigDTO inValidModuleConfig(final int moduleId) throws ServiceException;

}

interface ShopModuleVisibleService {

    void createShopModuleVisible(ShopModuleVisible shopModuleVisible) throws ServiceException;

    void createShopModuleVisibles(List<ShopModuleVisible> shopModuleVisibles) throws ServiceException;
}

interface DynamicDataHandlerService {

    @MessageMapping(routingKey = "arena.layout.invoice")
    void consumeInvoiceDynamic(InvoiceMessage message)throws ServiceException;


}

class ShopModuleVisible {


    private int moduleId;

    private String type;

    private int dynamicId;

    private String versionRange;

}

class ModuleDetailConfigDTO {

    private int moduleId;

    private String moduleConfig;

    private int moduleType;

    private String moduleName;

    private int containDynamicData;

    private int groupId;

    private String platform;

    private String permissionCode;

    private long moduleSequence;

    private boolean ifCanSee;

}

class ModuleConfig {



    private int moduleId;

    private int groupId;

    private String title;

    private String moduleName;

    private int containDynamicData;

    private int moduleType;

    private int sequence;

    private double rate;


    private Group group;

    private Message message;

    private Icon icon;

    private Img img;

    private Action action;


    private boolean ifCanSee;
}



@enduml
```