cc_binary(
    name = 'quotation_race_server',
    srcs = [
        'race_processor.cpp',
        'race_publish_quotation_handler.cpp',
        'race_quotation_handler.cpp',
        'race_quotation_chooser_impl.cpp',
        'race_statics_quotation_handler.cpp',
        'race_store_memcacheq_quotation_handler.cpp',
        'main.cc'
    ],
    deps = [
        '//cpp_platform/base:soldier_basecpp',
        '//cpp_platform/attr:soldier_attr',
        '//cpp_platform/thirdparty/libmemcached:memcached',
        '//cpp_platform/thirdparty/cppzmq:cppzmq',
        '//quotation_common/thrift:quotation_thrift',
        '#boost_program_options',
    ]
)