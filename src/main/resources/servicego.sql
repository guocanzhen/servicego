--售后处理servicego：
--1）平台：id、平台名称、平台编号、备注
--2023/4/10
CREATE TABLE servicego.platform (
	id varchar(32) NOT NULL COMMENT '主键id',
	num varchar(32) NULL COMMENT '平台编号',
	name varchar(32) NULL COMMENT '平台名称',
	bz varchar(255) NULL COMMENT '备注',
	create_time TIMESTAMP DEFAULT now() NULL COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT now() NULL COMMENT '更新时间',
	CONSTRAINT platform_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='平台';

--2）产品：id、产品名称、产品编号、产品组成、备注
--2023/4/10
CREATE TABLE servicego.product (
	id varchar(32) NOT NULL COMMENT '主键id',
	num varchar(32) NULL COMMENT '产品编号',
	name varchar(32) NULL COMMENT '产品名称',
	bz varchar(255) NULL COMMENT '备注',
	create_time TIMESTAMP DEFAULT now() NULL COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT now() NULL COMMENT '更新时间',
	CONSTRAINT product_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='产品';

--3）物流：id、公司编号、公司名称、备注
--2023/4/11
CREATE TABLE servicego.logistics (
	id varchar(32) NOT NULL COMMENT '主键id',
	num varchar(32) NULL COMMENT '公司编号',
	name varchar(32) NULL COMMENT '公司名称',
	bz varchar(255) NULL COMMENT '备注',
	create_time TIMESTAMP DEFAULT now() NULL COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT now() NULL COMMENT '更新时间',
	CONSTRAINT logistics_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='物流';

--4）售后单：id、平台、产品明细、单号、物流状态（枚举）、寄出日期、售后原因（文本）、处理客户（枚举）、处理机器（枚举）、产品问题、平台完结状态（枚举）、备注
--2023/4/11
CREATE TABLE servicego.after_sales_order (
	id varchar(32) NOT NULL COMMENT '主键id',
	platform_id varchar(32) NULL COMMENT '平台id',
	group_id varchar(32) NULL COMMENT '产品明细（挂单号）',
	bill_no varchar(100) NULL COMMENT '单号',
	logistics_status varchar(10) NULL COMMENT '物流状态：0-配送中；1-已到；',
	send_time TIMESTAMP NULL COMMENT '寄出日期',
	reason varchar(255) NULL COMMENT '售后原因',
	deal_way varchar(10) NULL COMMENT '处理客户：1-退货退款；',
	machine varchar(10) NULL COMMENT '处理机器：1-验货；',
	pro_matter varchar(10) NULL COMMENT '产品问题：1-没问题；2-已到货；3-报废配件包材；4-报废配件；',
	plat_end_status varchar(10) NULL COMMENT '平台完结状态：1-已退款；',
	bz varchar(255) NULL COMMENT '备注',
	create_time TIMESTAMP DEFAULT now() NULL COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT now() NULL COMMENT '更新时间',
	CONSTRAINT after_sales_order_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='售后单';

--5）产品明细组：id、售后单id、产品id
--2023/4/11
CREATE TABLE servicego.product_describe (
	id varchar(32) NOT NULL COMMENT '主键id',
	after_sales_order_id varchar(32) NOT NULL COMMENT '售后单id',
	product_id varchar(32) NOT NULL COMMENT '产品id',
	create_time TIMESTAMP DEFAULT now() NULL COMMENT '创建时间',
	update_time TIMESTAMP DEFAULT now() NULL COMMENT '更新时间',
	CONSTRAINT product_describe_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='产品明细';
