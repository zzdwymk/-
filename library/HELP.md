# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.3/maven-plugin/build-image.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


图书管理系统设计详解
前端功能与交互流程
前端采用Vue3 + Vite + Element Plus + Axios技术栈，根据功能模块划分页面，每个页面职责单一，交互流程清晰。登录页使用 Element Plus 的表单和输入组件，结合 Axios 向后端 /api/auth/login 接口提交用户名/密码和验证码，登录成功后将返回的 JWT Token 存入 localStorage
。同时可通过 Vuex（或 Pinia）管理登录状态，在 App 初始化时检查 localStorage 中的 Token，存在时调用 /api/auth/current 接口获取当前用户并更新全局状态
。登录后通过 Vue Router 导航到首页，未登录时可使用路由守卫（navigation guard）拦截需要鉴权的路由，跳转回登录页面以强制认证。

首页（仪表盘）通过图表展示系统数据统计，如使用 ECharts 等可视化库绘制借阅趋势折线图和分类比例饼图；页面上还放置快捷入口按钮，支持用户快速新增图书或登记借阅。图书管理页、读者管理页等列表页均使用 Element Plus 的 ElTable 和 ElPagination 组件展示数据
。例如，图书列表可通过后端接口传回包含总数和分页列表的数据，再绑定至 <el-table :data="list">，结合 <el-pagination> 实现分页显示
。表格列可配置排序、选择框等，并在需要时提供批量操作。上方可加入搜索框、分类筛选条件，以关键字段（如书名、作者）模糊查询、分类下拉筛选等过滤表格数据。表单新增/编辑时，通过 Element Plus 的 ElForm 进行输入校验，确保必填项正确。借阅登记页交互中，用户先选择要借出的图书和读者信息，页面自动填充当前借阅日期和根据系统参数计算的应还日期；超期警告可通过日期比较，在表格中将超期记录标红。归还登记页支持扫码或手动选择借阅记录，并自动计算超期罚款。系统设置页则使用 Form 表单管理管理员账号、角色权限、系统参数等，支持新增/编辑/禁用操作，并使用树形控件或多选复选框等组件配置角色权限。需要时，前端可通过 Vue Router 和侧边导航实现多页面跳转，页面间数据通过 Axios 异步请求后端接口获取，确保前端显示逻辑清晰、响应迅速。

后端接口设计与安全策略
后端使用Spring Boot 3.x + MyBatis-Plus + Spring Security + JWT，接口遵循 RESTful 规范，统一前缀 /api。所有接口返回统一 JSON 格式（code/msg/data）
，并通过全局异常处理器（如 @ControllerAdvice）捕获异常，返回一致的错误响应格式
。例如，可定义一个 Result 类封装返回值，并在 @RestControllerAdvice 中捕获各类异常映射为标准输出，增强系统健壮性
。

验证和鉴权： 登录接口 /api/auth/login 验证用户名密码正确后，使用 JWT 生成 Token 并返回给前端，Token 可使用 HMAC SHA256 签名并设置过期时间。前端将 Token 放入请求头 Authorization: Bearer <token> 发送后续请求。后端使用 Spring Security 拦截器，在网关层或配置类中配置 JWT 过滤器，根据 Token 验证用户身份。Spring Security 提供完善的认证与授权功能：如 BCrypt 加密密码存储、基于注解或配置方式进行接口权限控制（如 /api/system/* 仅 ADMIN 可访问）
。由于采用 JWT 实现无状态认证，可禁用默认 CSRF 保护（对纯前后分离的 API 请求可关闭 CSRF），同时开启 Spring Security 的防 XSS、限制登录失败次数等安全措施
。另外建议使用 HTTPS 加密传输，避免明文传递 Token 和敏感信息。接口层面应对所有输入进行严格校验，例如使用 Hibernate Validator 注解验证请求 DTO 参数的格式和范围
。对于验证失败或未登录/无权限访问，返回相应的 400/401/403 错误码和消息，并在前端做相应提示。

错误处理： 采用 Spring Boot 的统一异常处理模式，可定义业务异常类继承 RuntimeException 并包含自定义错误码，在 @ControllerAdvice 中针对不同异常类型（如验证失败、资源未找到）返回对应的 HTTP 状态码和错误信息
。例如对数据校验异常返回 400 状态，对未认证返回 401、权限不足返回 403。统一错误返回结构可复用上文的通用响应模板 {"code":..., "msg":..., "data":...}。

数据库设计优化
数据库采用 MySQL 8.0，核心表（用户、图书、读者、借阅、归还）已设计基本字段和关系。进一步优化时，可为关键查询字段添加索引，提升查询性能。例如：对 图书信息表(book_info) 的 book_no、book_name、author 等经常搜索的列建立索引；对 读者信息表(reader_info) 的 reader_no、name 建立索引；对 借阅记录表(borrow_record) 的 borrow_no、book_id、reader_id、status 建立索引；对 归还记录表(return_record) 的 return_no、borrow_id 建立索引
。尤其可考虑对多列组合查询创建联合索引，以提升覆盖查询效率，例如对 (reader_id, status)、(book_id, status) 建立复合索引，实现只查索引而不回表
。MySQL 索引优化是关键性能手段，可显著缩短查询时间
。表中已有的唯一约束（如 username, book_no, reader_no, borrow_no, return_no）可继续保留，并对外键增加约束保证引用完整性。此外，可在业务允许情况下为状态字段（如 status）使用 ENUM 类型（MySQL 中替代）以节省存储和避免脏数据。

对于数据量很大的表（如借阅记录和归还记录表在长期运行后可能达到数百万条以上），可考虑表分区提高性能和维护便捷性。使用 MySQL 的分区功能（RANGE、LIST、HASH、KEY 等策略）可以将大表按时间或特定规则拆分成多个物理分区，从而加速范围查询和归档操作
。例如按借阅日期做 RANGE 分区、或按状态做 LIST 分区。分区能让查询只扫描相关分区，提升大表查询性能
。总之，数据库设计应根据业务负载规划适当索引和分区策略，以兼顾灵活性和性能。

部署与运维
部署方案： 后端可打包为 Spring Boot 可执行 Jar（内置 Tomcat），也可使用 Docker 容器化部署。推荐使用 多阶段构建 的 Dockerfile，将构建和运行环境分离，生成体积精简的运行镜像
。生产环境中需要配置环境变量或配置文件（application-prod.yml）区分开发/生产参数，例如数据库连接、JWT 密钥等。后端部署时可使用反向代理（如 Nginx）统一映射前端和后端地址。前端在开发完成后使用 Vite 构建为静态资源，可部署在 Nginx 或 CDN 上，保证访问速度和安全。可以在 CI/CD 流程（如 GitHub Actions、Jenkins）中自动化打包和发布镜像。

监控方案： 建议集成 Spring Boot Actuator 提供运行时指标（如健康检查 /actuator/health、度量 /actuator/metrics 等）。结合 Prometheus + Grafana 搭建监控平台，前端可通过 /actuator/prometheus 暴露指标，Prometheus 定时抓取并在 Grafana 可视化各种性能数据
。这种方式已被广泛采用，实现应用性能的可视化监控
。同时配置应用日志收集，可使用 ELK（Elasticsearch/Logstash/Kibana）或 Splunk 等工具集中管理日志，便于排查问题和分析业务。容器化部署时可使用 Docker/Kubernetes 的健康检查（liveness、readiness probes）机制，确保容器崩溃时自动重启
。数据库方面可启用备份和复制方案（见下述）保证高可用。

备份与恢复： 对数据库应制定定期备份策略。常见方案是每日做增量备份、每周做全量备份，并保留多期备份文件
。可以使用 mysqldump 导出 SQL 文本，或物理备份工具（xtrabackup）。同时开启 MySQL 二进制日志（binlog），在需要时可进行增量恢复。备份文件应存放在异地（如云存储）以防硬件故障
。前端源码和后端代码应使用版本控制（如 Git），并定期打标签发布，确保快速回滚。必要时可建立数据库主从复制，提高容灾能力。

性能优化： 除数据库索引外，后端可使用缓存（如 Redis）缓存热点数据（图书信息、读者信息、系统参数），减少数据库压力。MyBatis-Plus 可配合二级缓存或 Redis 来缓存查询结果。还可在频繁访问的数据表中增加分页查询的缓存。使用 HikariCP 等高效连接池优化数据库连接。对于文件上传/Excel导入等耗时操作可采用异步处理或消息队列（RabbitMQ/Kafka）以避免阻塞请求。前端性能方面，Vite 默认支持代码分割和 Tree Shaking，将路由组件懒加载以减少首屏包大小
；使用 CDN 和启用 Gzip 压缩静态资源也是常见做法
。这些前端优化措施可将首屏加载时间缩减到可接受范围
。

测试方案及示例
测试用例： 后端可编写 JUnit 5 单元测试和集成测试，使用 @WebMvcTest 或 MockMvc/MockBean 模拟接口调用，验证各个 Controller、Service 方法的正确性
。例如对 /api/books 的 CRUD 接口进行单元测试：模拟请求参数并验证返回的 code/msg 是否符合预期，以及数据库的变化。前端可采用 Jest + Vue Test Utils 对组件进行单元测试
。登录页、表单组件等可写测试用例：通过 Jest mount 组件，模拟用户输入并触发提交事件，验证组件行为正确。

API 调用示例： 例如登录接口使用 POST /api/auth/login，请求示例：{"username":"admin","password":"123456"}，成功返回：{"code":200,"msg":"操作成功","data":{"token":"xxx.yyy.zzz","userInfo":{"id":1,"name":"管理员","role":"ADMIN"}}}。前端在登录成功后可将 data.token 存入 localStorage，并在后续调用中统一设置 Authorization 请求头。另一示例：分页查询图书 GET /api/books?pageNum=1&pageSize=10&bookName=Java，后端返回分页结果：{"code":200,"msg":"操作成功","data":{"total":100,"list":[{"id":1,"bookNo":"123","bookName":"Java编程思想",…},…]}}。前端可通过 Axios 封装请求：axios.get('/api/books', { params: { pageNum, pageSize, bookName } })，并将响应数据绑定到 Element Plus 表格与分页组件。

前端组件实现建议： 登录组件使用 <el-form> 包裹 <el-input>，配置 v-model 绑定用户名、密码和验证码字段，表单验证规则可限制非空。提交表单时调用登录接口并处理错误提示。列表页组件可拆分为表格组件和工具栏组件。例如图书列表可有 BookTable.vue（包含 ElTable、ElPagination）和 BookFilter.vue（包含搜索输入、分类下拉）两个子组件，父组件负责数据请求和状态管理。这种组件拆分有助于复用和维护。借阅和归还页可复用一个基础表格组件，并在其上动态添加“登记借阅”或“登记归还”按钮。对于批量导入功能，可使用 Element Plus 的 <el-upload> 组件支持文件上传，后端接口返回导入结果后前端给出成功/失败提示。整体上，以 Element Plus 现成组件为基础，结合 Vue 3 的组合式 API 组织逻辑，比如使用 setup() 函数和 ref/reactive 来管理表单状态和列表数据，能让代码清晰简洁。

核心建议： 前后端都应编写充足的单元和集成测试；接口文档可使用 Swagger 生成并提供示例。前端多组件复用、后端接口幂等性与安全性处理、数据库的索引与备份等方面，都需综合考虑以保证系统健壮、高效和可维护。

参考资料： 关于 Element Plus 表格与分页的使用可参考相关博客实例
；Vue3 登录状态持久化可参考示例
；Spring Boot 全局异常处理与统一响应可参考
；Spring Security+JWT 认证方案可参考
；MySQL 索引优化与分区策略可参考
；性能优化包括前端代码分割和后端监控部署可参考
等。