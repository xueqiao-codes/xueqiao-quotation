cc_binary(
    name = 'esunny_access',
    srcs = [
        'esunny_access.cpp',
        'esunny_quote_handler_impl.cpp',
        'esunny_quote_proxy.cpp',
        'esunny_quote_worker.cpp',
    ],
    deps = [
        '//quotation_common/quotation/access/common:access_common',
        '//quotation_common/quotation/race/client:quotation_race_client',
        '//cpp_platform/thirdparty/esunny9:esunny9',
        '//cpp_platform/base:soldier_basecpp',
        '#boost_date_time',
    ]
)