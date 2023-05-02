#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class OAIKotlinThrowable, OAIOpenAICompanion, OAIAIModelPermission, OAIAIModel, OAIChatMessage, NSData, OAIKotlinArray<T>, OAIKotlinException, OAIKotlinRuntimeException, OAIKotlinIllegalStateException;

@protocol OAIOpenAIAudioTranscriptions, OAIOpenAIAudioTranslations, OAIOpenAIChatCompletions, OAIOpenAICompletion, OAIOpenAIEdits, OAIOpenAIImageGenerations, OAIOpenAIListModels, OAIOpenAIRetrieveModel, OAIOpenAI, OAIOpenAICallback, OAIKotlinIterator;

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
@interface OAIBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface OAIBase (OAIBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface OAIMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface OAIMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorOAIKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface OAINumber : NSNumber
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
@interface OAIByte : OAINumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface OAIUByte : OAINumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface OAIShort : OAINumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface OAIUShort : OAINumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface OAIInt : OAINumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface OAIUInt : OAINumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface OAILong : OAINumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface OAIULong : OAINumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface OAIFloat : OAINumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface OAIDouble : OAINumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface OAIBoolean : OAINumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("OpenAI")))
@protocol OAIOpenAI
@required
- (id<OAIOpenAIAudioTranscriptions>)audioTranscriptions __attribute__((swift_name("audioTranscriptions()")));
- (id<OAIOpenAIAudioTranslations>)audioTranslations __attribute__((swift_name("audioTranslations()")));
- (id<OAIOpenAIChatCompletions>)chatCompletions __attribute__((swift_name("chatCompletions()")));
- (id<OAIOpenAICompletion>)completion __attribute__((swift_name("completion()")));
- (id<OAIOpenAIEdits>)edits __attribute__((swift_name("edits()")));
- (id<OAIOpenAIImageGenerations>)imageGenerations __attribute__((swift_name("imageGenerations()")));
- (id<OAIOpenAIListModels>)listModels __attribute__((swift_name("listModels()")));
- (id<OAIOpenAIRetrieveModel>)retrieveModel __attribute__((swift_name("retrieveModel()")));
@end

__attribute__((swift_name("OpenAICallback")))
@protocol OAIOpenAICallback
@required
- (void)onErrorThrowable:(OAIKotlinThrowable *)throwable __attribute__((swift_name("onError(throwable:)")));
- (void)onSuccessResult:(id _Nullable)result __attribute__((swift_name("onSuccess(result:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("OpenAICompanion")))
@interface OAIOpenAICompanion : OAIBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) OAIOpenAICompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (id<OAIOpenAI>)createApiKey:(NSString *)apiKey __attribute__((swift_name("create(apiKey:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AIModel")))
@interface OAIAIModel : OAIBase
- (instancetype)initWithId:(NSString *)id ownedBy:(NSString *)ownedBy permission:(NSArray<OAIAIModelPermission *> *)permission __attribute__((swift_name("init(id:ownedBy:permission:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<OAIAIModelPermission *> *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (OAIAIModel *)doCopyId:(NSString *)id ownedBy:(NSString *)ownedBy permission:(NSArray<OAIAIModelPermission *> *)permission __attribute__((swift_name("doCopy(id:ownedBy:permission:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *ownedBy __attribute__((swift_name("ownedBy")));
@property (readonly) NSArray<OAIAIModelPermission *> *permission __attribute__((swift_name("permission")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AIModelPermission")))
@interface OAIAIModelPermission : OAIBase
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
- (OAIAIModelPermission *)doCopyId:(NSString *)id allowCreateEngine:(BOOL)allowCreateEngine allowSampling:(BOOL)allowSampling allowLogProbs:(BOOL)allowLogProbs allowSearchIndices:(BOOL)allowSearchIndices allowView:(BOOL)allowView allowFineTuning:(BOOL)allowFineTuning organization:(NSString *)organization isBlocking:(BOOL)isBlocking __attribute__((swift_name("doCopy(id:allowCreateEngine:allowSampling:allowLogProbs:allowSearchIndices:allowView:allowFineTuning:organization:isBlocking:)")));
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
@interface OAIChatMessage : OAIBase
- (instancetype)initWithRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("init(role:content:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (OAIChatMessage *)doCopyRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("doCopy(role:content:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly) NSString *role __attribute__((swift_name("role")));
@end

__attribute__((swift_name("OpenAIAudioTranscriptions")))
@protocol OAIOpenAIAudioTranscriptions
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeFilename:(NSString *)filename audioFile:(NSData *)audioFile completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(filename:audioFile:completionHandler:)")));
- (void)executeFilename:(NSString *)filename audioFile:(NSData *)audioFile callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(filename:audioFile:callback:)")));
- (id<OAIOpenAIAudioTranscriptions>)setLanguageLanguage:(NSString *)language __attribute__((swift_name("setLanguage(language:)")));
- (id<OAIOpenAIAudioTranscriptions>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<OAIOpenAIAudioTranscriptions>)setPromptPrompt:(NSString *)prompt __attribute__((swift_name("setPrompt(prompt:)")));
- (id<OAIOpenAIAudioTranscriptions>)setResponseFormatFormat:(NSString *)format __attribute__((swift_name("setResponseFormat(format:)")));
- (id<OAIOpenAIAudioTranscriptions>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
@end

__attribute__((swift_name("OpenAIAudioTranslations")))
@protocol OAIOpenAIAudioTranslations
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeFilename:(NSString *)filename audioFile:(NSData *)audioFile completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(filename:audioFile:completionHandler:)")));
- (void)executeFilename:(NSString *)filename audioFile:(NSData *)audioFile callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(filename:audioFile:callback:)")));
- (id<OAIOpenAIAudioTranslations>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<OAIOpenAIAudioTranslations>)setPromptPrompt:(NSString *)prompt __attribute__((swift_name("setPrompt(prompt:)")));
- (id<OAIOpenAIAudioTranslations>)setResponseFormatFormat:(NSString *)format __attribute__((swift_name("setResponseFormat(format:)")));
- (id<OAIOpenAIAudioTranslations>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
@end

__attribute__((swift_name("OpenAIChatCompletions")))
@protocol OAIOpenAIChatCompletions
@required
- (id<OAIOpenAIChatCompletions>)addMessageRole:(NSString *)role content:(NSString *)content __attribute__((swift_name("addMessage(role:content:)")));

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeContent:(NSString *)content completionHandler:(void (^)(NSArray<OAIChatMessage *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(content:completionHandler:)")));
- (void)executeContent:(NSString *)content callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(content:callback:)")));
- (id<OAIOpenAIChatCompletions>)setMaxResultsResults:(int32_t)results __attribute__((swift_name("setMaxResults(results:)")));
- (id<OAIOpenAIChatCompletions>)setMaxTokensTokens:(int32_t)tokens __attribute__((swift_name("setMaxTokens(tokens:)")));
- (id<OAIOpenAIChatCompletions>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<OAIOpenAIChatCompletions>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<OAIOpenAIChatCompletions>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("OpenAICompletion")))
@protocol OAIOpenAICompletion
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeWithCompletionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(completionHandler:)")));
- (void)executeCallback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(callback:)")));
- (id<OAIOpenAICompletion>)saveHistoryIsSaveHistory:(BOOL)isSaveHistory __attribute__((swift_name("saveHistory(isSaveHistory:)")));
- (id<OAIOpenAICompletion>)setInputInput:(NSString *)input __attribute__((swift_name("setInput(input:)")));
- (id<OAIOpenAICompletion>)setMaxTokensTokens:(int32_t)tokens __attribute__((swift_name("setMaxTokens(tokens:)")));
- (id<OAIOpenAICompletion>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<OAIOpenAICompletion>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<OAIOpenAICompletion>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("OpenAIEdits")))
@protocol OAIOpenAIEdits
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeInstruction:(NSString *)instruction completionHandler:(void (^)(NSArray<NSString *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(instruction:completionHandler:)")));
- (void)executeInstruction:(NSString *)instruction callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(instruction:callback:)")));
- (id<OAIOpenAIEdits>)setInputInput:(NSString *)input __attribute__((swift_name("setInput(input:)")));
- (id<OAIOpenAIEdits>)setModelModel:(NSString *)model __attribute__((swift_name("setModel(model:)")));
- (id<OAIOpenAIEdits>)setResultsResults:(int32_t)results __attribute__((swift_name("setResults(results:)")));
- (id<OAIOpenAIEdits>)setTemperatureTemperature:(double)temperature __attribute__((swift_name("setTemperature(temperature:)")));
- (id<OAIOpenAIEdits>)setTopPTopP:(double)topP __attribute__((swift_name("setTopP(topP:)")));
@end

__attribute__((swift_name("OpenAIImageGenerations")))
@protocol OAIOpenAIImageGenerations
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executePrompt:(NSString *)prompt completionHandler:(void (^)(NSArray<NSString *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(prompt:completionHandler:)")));
- (void)executePrompt:(NSString *)prompt callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(prompt:callback:)")));
- (id<OAIOpenAIImageGenerations>)setResponseFormatResponseFormat:(NSString *)responseFormat __attribute__((swift_name("setResponseFormat(responseFormat:)")));
- (id<OAIOpenAIImageGenerations>)setResultsResults:(int32_t)results __attribute__((swift_name("setResults(results:)")));
- (id<OAIOpenAIImageGenerations>)setSizeSize:(NSString *)size __attribute__((swift_name("setSize(size:)")));
@end

__attribute__((swift_name("OpenAIListModels")))
@protocol OAIOpenAIListModels
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeWithCompletionHandler:(void (^)(NSArray<OAIAIModel *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(completionHandler:)")));
- (void)executeCallback_:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(callback_:)")));
@end

__attribute__((swift_name("OpenAIRetrieveModel")))
@protocol OAIOpenAIRetrieveModel
@required

/**
 * @note This method converts instances of CancellationException, YChatException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeId:(NSString *)id completionHandler:(void (^)(OAIAIModel * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(id:completionHandler:)")));
- (void)executeId:(NSString *)id callback:(id<OAIOpenAICallback>)callback __attribute__((swift_name("execute(id:callback:)")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface OAIKotlinThrowable : OAIBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (OAIKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) OAIKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface OAIKotlinException : OAIKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface OAIKotlinRuntimeException : OAIKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface OAIKotlinIllegalStateException : OAIKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface OAIKotlinCancellationException : OAIKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ychat_coreYChatException")))
@interface OAIYchat_coreYChatException : OAIKotlinException
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause statusCode:(OAIInt * _Nullable)statusCode __attribute__((swift_name("init(cause:statusCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause statusCode:(OAIInt * _Nullable)statusCode __attribute__((swift_name("init(message:cause:statusCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(OAIKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property OAIInt * _Nullable statusCode __attribute__((swift_name("statusCode")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface OAIKotlinArray<T> : OAIBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(OAIInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<OAIKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol OAIKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
