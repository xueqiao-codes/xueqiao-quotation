cc_library(
    name = 'subscribe_api',
    srcs = [
        'api/group_receivor.cpp',
        'api/subscribe_system.cpp',
    ], 
    deps = [
        '//quotation_common/quotation/race/common:quotation_race_common'
    ],
    export_incs = [
        '../..'
    ]
)

cc_library(
    name = "subscribe_api_swig",
    srcs = [
        'swig/subscribe_system_swig.cpp',
    ],
    deps = [
        ':subscribe_api',
    ]
)

swig_library(
    name = 'QuotationSubscriber',
    srcs = [
        'swig/QuotationSubscriber.i',
    ],
    java_package = 'xueqiao.quotation.subscriber',
    java_lib_packed = True,
    deps = [
        ':subscribe_api_swig'
    ]
)