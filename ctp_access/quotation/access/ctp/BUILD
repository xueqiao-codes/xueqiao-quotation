cc_binary(
    name = 'ctp_access',
    srcs = [
        'ctp_access.cpp',
        'ctp_access_processor.cpp',
        'ctp_md_proxy.cpp',
        'ctp_trader_proxy.cpp',
    ],
    deps = [
        '//quotation_common/quotation/access/common:access_common',
        '//quotation_common/quotation/race/client:quotation_race_client',
        '//cpp_platform/thirdparty/ctp2/release:ctp2-release',
        '//cpp_platform/base:soldier_basecpp',
        '#boost_date_time',
    ]
)