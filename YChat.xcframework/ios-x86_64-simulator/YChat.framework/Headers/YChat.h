#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class YChatKotlinThrowable, YChatYChatCompanion, YChatAIModelPermission, YChatAIModel, YChatChatMessage, YChatKotlinArray<T>, YChatKotlinException, YChatKotlinRuntimeException, YChatKotlinIllegalStateException;

@protocol YChatChatCompletions, YChatCompletion, YChatEdits, YChatImageGenerations, YChatListModels, YChatRetrieveModel, YChatYChat, YChatYChatCallback, YChatKotlinIterator;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface YChatBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface YChatBase (YChatBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface YChatMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface YChatMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorYChatKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface YChatNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface YChatByte : YChatNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface YChatUByte : YChatNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface YChatShort : YChatNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface YChatUShort : YChatNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface YChatInt : YChatNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface YChatUInt : YChatNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface YChatLong : YChatNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface YChatULong : YChatNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface YChatFloat : YChatNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface YChatDouble : YChatNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface YChatBoolean : YChatNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("YChat")))
@protocol YChatYChat
@required
- (id<YChatChatCompletions>)chatCompletions __attribute__((swift_name("chatCompletions()")));
- (id<YChatCompletion>)completion __attribute__((swift_name("completion()")));
- (id<YChatEdits>)edits __attribute__((swift_name("edits()")));
- (id<YChatImageGenerations>)imageGenerations __attribute__((swift_name("imageGenerations()")));
- (id<YChatListModels>)listModels __attribute__((swift_name("listModels()")));
- (id<YChatRetrieveModel>)retrieveModel __attribute__((swift_name("retrieveModel()")));
@end

__attribute__((swift_name("YChatCallback")))
@protocol YChatYChatCallback
@required
- (void)onErrorThrowable:(YChatKotlinThrowable *)throwable __attribute__((swift_name("onError(throwable:)")));
- (void)onSuccessResult:(id _Nullable)result __attribute__((swift_name("onSuccess(result:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("YChatCompanion")))
@interface YChatYChatCompanion : YChatBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) YChatYChatCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (id<YChatYChat>)createApiKey:(NSString *)apiKey __attribute__((swift_name("create(apiKey:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AIModel")))
@interface YChatAIModel : YChatBase
- (instancetype)initWithId:(NSString *)id ownedBy:(NSString *)ownedBy permission:(NSArray<YChatAIModelPermission *> *)permission __attribute__((swift_name("init(id:ownedBy:permission:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<YChatAIModelPermission *> *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (YChatAIModel *)doCopyId:(NSString *)id ownedBy:(NSString *)ownedBy permission:(NSArray<YChatAIModelPermission *> *)permission __attribute__((swift_name("doCopy(id:ownedBy:permission:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *ownedBy __attribute__((swift_name("ownedBy")));
@property (readonly) NSArray<YChatAIModelPermission *> *permission __attribute__((swift_name("permission")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AIModelPermission")))
@interface YChatAIModelPermission : YChatBase
- (instancetype)initWithId:(NSString *)id allowCreateEngine:(BOOL)allowCreateEngine allowSampling:(BOOL)allowSampling allowLogProbs:(BOOL)allowLogProbs allowSearchIndices:(BOOL)allowSearchIndices allowView:(BOOL)allowView allowFineTuning:(BOOL)allowFineTuning organization:(NSString *)organization isBlocking:(BOOL)isBlocking __attribute__((swift_name("init(id:allowCreateEngine:allowSampling:allowLogProbs:allowSearchIndices:allowView:allowFineTuning:organization:isBlocking:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component8 __attribute__((swift_name("component8()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component9 __attribute__((swift_name("component9()"))) __attribute__((deprecated("use corresponding property instead")));
- (YChatAIModelPermission *)doCopyId:(NSString *)id allowCreateEngine:(BOOL)allowCreateEngine allowSampling:(BOOL)allowSampling allowLogProbs:(BOOL)allowLogProbs allowSearchIndices:(BOOL)allowSearchIndices allowView:(BOOL)allowView allowFineTuning:(BOOL)allowFineTuning organization:(NSString *)organization isBlocking:(BOOL)isBlocking __attribute__((swift_name("doCopy(id:allowCreateEngine:allowSampling:allowLogProbs:allowSearchIndices:allowView:allowFineTuning:organization:isBlocking:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL allowCreateEngine __attribute__((swift_name("allowCreateEngine")));
@property (readonly) BOOL allowFineTuning __attribute__((swift_name("allowFineTuning")));
@property (readonly) BOOL allowLogProbs __attribute__((swift_name("allowLogProbs")));
@property (readonly) BOOL allowSampling __attribute__((swift_name("allowSampling")));
@property (readonly) BOOL allowSearchIndices __attribute__((swift_name("allowSearchIndices")));
@property (readonly) BOOL allowView __attribute__((swift_name("allowView")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isBlocking __attribute__((swift_name("isBlocking")));
@property (readonly) NSString *organization __attribute__((swift_name("organization")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatMessage")))
@interface YChatChatMessage : YChatBase
- (instancetype)initWithRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("init(role:content:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (YChatChatMessage *)doCopyRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("doCopy(role:content:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly) NSString *role __attribute__((swift_name("role")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface YChatKotlinThrowable : YChatBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (YChatKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) YChatKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface YChatKotlinException : YChatKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatGptException")))
@interface YChatChatGptException : YChatKotlinException
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause statusCode:(YChatInt * _Nullable)statusCode __attribute__((swift_name("init(cause:statusCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause statusCode:(YChatInt * _Nullable)statusCode __attribute__((swift_name("init(message:cause:statusCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property YChatInt * _Nullable statusCode __attribute__((swift_name("statusCode")));
@end

__attribute__((swift_name("ChatCompletions")))
@protocol YChatChatCompletions
@required
- (id<YChatChatCompletions>)addMessageRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("addMessage(role:content:)")));

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeContent:(NSString *)content completionHandler:(void (^)(NSArray<YChatChatMessage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(content:completionHandler:)")));
- (void)executeContent:(NSString *)content callback:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(content:callback:)")));
- (id<YChatChatCompletions>)setMaxResultsResults:(int32_t)results __attribute__((swift_name("setMaxResults(results:)")));
- (id<YChatChatCompletions>)setMaxTokensTokens:(int32_t)tokens __attribute__((swift_name("setMaxTokens(tokens:)")));
- (id<YChatChatCompletions>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<YChatChatCompletions>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<YChatChatCompletions>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("Completion")))
@protocol YChatCompletion
@required

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeWithCompletionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(completionHandler:)")));
- (void)executeCallback:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(callback:)")));
- (id<YChatCompletion>)saveHistoryIsSaveHistory:(BOOL)isSaveHistory __attribute__((swift_name("saveHistory(isSaveHistory:)")));
- (id<YChatCompletion>)setInputInput:(NSString *)input __attribute__((swift_name("setInput(input:)")));
- (id<YChatCompletion>)setMaxTokensTokens:(int32_t)tokens __attribute__((swift_name("setMaxTokens(tokens:)")));
- (id<YChatCompletion>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<YChatCompletion>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<YChatCompletion>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("Edits")))
@protocol YChatEdits
@required

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeInstruction:(NSString *)instruction completionHandler:(void (^)(NSArray<NSString *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(instruction:completionHandler:)")));
- (void)executeInstruction:(NSString *)instruction callback:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(instruction:callback:)")));
- (id<YChatEdits>)setInputInput:(NSString *)input __attribute__((swift_name("setInput(input:)")));
- (id<YChatEdits>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<YChatEdits>)setResultsResults:(int32_t)results __attribute__((swift_name("setResults(results:)")));
- (id<YChatEdits>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<YChatEdits>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("ImageGenerations")))
@protocol YChatImageGenerations
@required

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executePrompt:(NSString *)prompt completionHandler:(void (^)(NSArray<NSString *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(prompt:completionHandler:)")));
- (void)executePrompt:(NSString *)prompt callback:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(prompt:callback:)")));
- (id<YChatImageGenerations>)setResponseFormatResponseFormat:(NSString *)responseFormat __attribute__((swift_name("setResponseFormat(responseFormat:)")));
- (id<YChatImageGenerations>)setResultsResults:(int32_t)results __attribute__((swift_name("setResults(results:)")));
- (id<YChatImageGenerations>)setSizeSize:(NSString *)size __attribute__((swift_name("setSize(size:)")));
@end

__attribute__((swift_name("ListModels")))
@protocol YChatListModels
@required

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeWithCompletionHandler:(void (^)(NSArray<YChatAIModel *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(completionHandler:)")));
- (void)executeCallback_:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(callback_:)")));
@end

__attribute__((swift_name("RetrieveModel")))
@protocol YChatRetrieveModel
@required

/**
 * @note This method converts instances of CancellationException, ChatGptException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeId:(NSString *)id completionHandler:(void (^)(YChatAIModel * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(id:completionHandler:)")));
- (void)executeId:(NSString *)id callback:(id<YChatYChatCallback>)callback __attribute__((swift_name("execute(id:callback:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface YChatKotlinArray<T> : YChatBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(YChatInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<YChatKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface YChatKotlinRuntimeException : YChatKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface YChatKotlinIllegalStateException : YChatKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface YChatKotlinCancellationException : YChatKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(YChatKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol YChatKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
